package cn.wycode.train.controller;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

import cn.wycode.train.entity.RouteImage;

/**
 * 火车控制器
 * Created by wangyu on 16/4/11.
 */
public class TrainController {

    static final String TAG = "TrainController";

    View trainView;

    TrainEvent trainEvent;

    AnimatorListener listener;

    float mapHeight;

    LinearInterpolator interpolator = new LinearInterpolator();

    static final int prepareTime = 2 * 1000; //预备时间(进入画布)
    static final int passTime = 6 * 1000; //通过时间


    TrainAnimationFactory animationFactory;


    public TrainController(View trainView, float mapHeight) {
        this.trainView = trainView;
        this.listener = new AnimatorListener();
        this.mapHeight = mapHeight;

        animationFactory = new TrainAnimationFactory(mapHeight);
    }

    //先进入画布
    public void start() {
        ObjectAnimator animator = animationFactory.getPreparedAnimator(trainView);

        animator.setDuration(prepareTime);

        animator.addListener(listener);

        animator.start();

    }


    public void next(RouteImage nextImage) {

        if (nextImage.type == RouteImage.TYPE_LINE) {
            passLine(nextImage);

        } else if (nextImage.type == RouteImage.TYPE_ANGLE) {
            passAngel(nextImage);
        }


    }

    private void passAngel(RouteImage nextImage) {
        AnimatorSet bouncer;

        if (nextImage.imageDirection == RouteImage.DIRECTION_LEFT) {
            if (trainView.getRotation() % 360 == 90 || trainView.getRotation() % 360 == -270) {
                bouncer = animationFactory.getBottom2Left(trainView);
            } else if (trainView.getRotation() % 360 == 180 || trainView.getRotation() % 360 == -180) {
                bouncer = animationFactory.getLeft2Bottom(trainView);

            } else {
                trainEvent.gameOver();
                return;
            }
        } else if (nextImage.imageDirection == RouteImage.DIRECTION_TOP) {
            if (trainView.getRotation() % 360 == -90 || trainView.getRotation() % 360 == 270) {
                bouncer = animationFactory.getTop2Left(trainView);
            } else if (trainView.getRotation() % 360 == 180 || trainView.getRotation() % 360 == -180) {
                bouncer = animationFactory.getLeft2Top(trainView);
            } else {
                trainEvent.gameOver();
                return;
            }
        } else if(nextImage.imageDirection == RouteImage.DIRECTION_RIGHT){
            if (trainView.getRotation() % 360 == -90 || trainView.getRotation() % 360 == 270) {
                bouncer = animationFactory.getTop2Right(trainView);
            } else if (trainView.getRotation() % 360 == 0) {
                bouncer = animationFactory.getRight2Top(trainView);
            } else {
                trainEvent.gameOver();
                return;
            }
        } else if(nextImage.imageDirection == RouteImage.DIRECTION_BOTTOM){
            if (trainView.getRotation() % 360 == 90 || trainView.getRotation() % 360 == -270) {
                bouncer = animationFactory.getBottom2Right(trainView);
            } else if (trainView.getRotation() % 360 == 0) {
                bouncer = animationFactory.getRight2Bottom(trainView);
            } else {
                trainEvent.gameOver();
                return;
            }
        }else{

            return;
        }

        bouncer.setDuration(passTime);

        bouncer.addListener(listener);
        bouncer.start();
    }

    private void passLine(RouteImage nextImage) {
        ObjectAnimator animator;
        if (nextImage.imageDirection == RouteImage.DIRECTION_LEFT
                || nextImage.imageDirection == RouteImage.DIRECTION_RIGHT) {

            if (trainView.getRotation() % 360 == 0) {
                animator = animationFactory.getRight2Left(trainView);

            } else if (trainView.getRotation() % 360 == 180
                    || trainView.getRotation() % 360 == -180) {
                animator = animationFactory.getLeft2Right(trainView);
            } else {
                trainEvent.gameOver();
                return;
            }
        } else if (nextImage.imageDirection == RouteImage.DIRECTION_TOP
                || nextImage.imageDirection == RouteImage.DIRECTION_BOTTOM) {

            if (trainView.getRotation() % 360 == 270
                    || trainView.getRotation() % 360 == -90) {
                animator = animationFactory.getTop2Bottom(trainView);

            } else if (trainView.getRotation() % 360 == 90
                    || trainView.getRotation() % 360 == -270) {
                animator = animationFactory.getBottom2Top(trainView);
            } else {
                trainEvent.gameOver();
                return;
            }
        } else {

            return;
        }
        animator.setDuration(passTime);

        animator.addListener(listener);

        animator.start();
    }


    public interface TrainEvent {
        void next();

        void gameOver();
    }

    public void setListener(TrainEvent event) {
        this.trainEvent = event;
    }


    class AnimatorListener implements Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            if (trainEvent != null) {
                trainEvent.next();
            }
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }


}
