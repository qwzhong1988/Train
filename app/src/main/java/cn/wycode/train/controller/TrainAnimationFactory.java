package cn.wycode.train.controller;

import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * 火车动画工厂
 * Created by wangyu on 16/4/11.
 */
public class TrainAnimationFactory {

    LinearInterpolator interpolator = new LinearInterpolator();

    float mapRectHeight;


    public TrainAnimationFactory(float mapRectHeight) {
        this.mapRectHeight = mapRectHeight;
    }

    public ObjectAnimator getPreparedAnimator(View view) {

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", -view.getWidth() / 2);

        animator.setInterpolator(interpolator);

        return animator;
    }


    public AnimatorSet getLeft2Bottom(View view) {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "translationX", view.getTranslationX(), mapRectHeight / 2);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), mapRectHeight / 2);
        ObjectAnimator animatorRotate = ObjectAnimator.ofFloat(view, "rotation", 180, 270);

        animatorX.setEvaluator(new SinEvaluator());
        animatorY.setEvaluator(new CosEvaluator());

        AnimatorSet bouncer = new AnimatorSet();
        bouncer.playTogether(animatorX, animatorY, animatorRotate);

        bouncer.setInterpolator(interpolator);

        return bouncer;
    }

    public AnimatorSet getBottom2Left(View view) {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "translationX", view.getTranslationX(), -mapRectHeight / 2);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), -mapRectHeight / 2);
        ObjectAnimator animatorRotate = ObjectAnimator.ofFloat(view, "rotation", 90, 0);

        animatorX.setEvaluator(new CosEvaluator());
        animatorY.setEvaluator(new SinEvaluator());

        AnimatorSet bouncer = new AnimatorSet();
        bouncer.playTogether(animatorX, animatorY, animatorRotate);

        bouncer.setInterpolator(interpolator);

        return bouncer;
    }

    public AnimatorSet getLeft2Top(View view) {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "translationX", view.getTranslationX(), mapRectHeight / 2);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), -mapRectHeight / 2);
        ObjectAnimator animatorRotate = ObjectAnimator.ofFloat(view, "rotation", 180, 90);

        animatorX.setEvaluator(new SinEvaluator());
        animatorY.setEvaluator(new CosEvaluator());

        AnimatorSet bouncer = new AnimatorSet();
        bouncer.playTogether(animatorX, animatorY, animatorRotate);

        bouncer.setInterpolator(interpolator);

        return bouncer;
    }

    public AnimatorSet getTop2Left(View view) {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "translationX", view.getTranslationX(), -mapRectHeight / 2);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), mapRectHeight / 2);
        ObjectAnimator animatorRotate = ObjectAnimator.ofFloat(view, "rotation", 270, 360);

        animatorX.setEvaluator(new CosEvaluator());
        animatorY.setEvaluator(new SinEvaluator());

        AnimatorSet bouncer = new AnimatorSet();
        bouncer.playTogether(animatorX, animatorY, animatorRotate);

        bouncer.setInterpolator(interpolator);

        return bouncer;
    }

    public AnimatorSet getTop2Right(View view) {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "translationX", view.getTranslationX(), mapRectHeight / 2);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), mapRectHeight / 2);
        ObjectAnimator animatorRotate = ObjectAnimator.ofFloat(view, "rotation", 270, 180);

        animatorX.setEvaluator(new CosEvaluator());
        animatorY.setEvaluator(new SinEvaluator());

        AnimatorSet bouncer = new AnimatorSet();
        bouncer.playTogether(animatorX, animatorY, animatorRotate);

        bouncer.setInterpolator(interpolator);

        return bouncer;
    }

    public AnimatorSet getRight2Top(View view) {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "translationX", view.getTranslationX(), -mapRectHeight / 2);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), -mapRectHeight / 2);
        ObjectAnimator animatorRotate = ObjectAnimator.ofFloat(view, "rotation", 0, 90);

        animatorX.setEvaluator(new SinEvaluator());
        animatorY.setEvaluator(new CosEvaluator());

        AnimatorSet bouncer = new AnimatorSet();
        bouncer.playTogether(animatorX, animatorY, animatorRotate);

        bouncer.setInterpolator(interpolator);

        return bouncer;
    }


    public AnimatorSet getRight2Bottom(View view) {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "translationX", view.getTranslationX(), -mapRectHeight / 2);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), mapRectHeight / 2);
        ObjectAnimator animatorRotate = ObjectAnimator.ofFloat(view, "rotation", 0, -90);


        animatorX.setEvaluator(new SinEvaluator());
        animatorY.setEvaluator(new CosEvaluator());

        AnimatorSet bouncer = new AnimatorSet();
        bouncer.playTogether(animatorX, animatorY, animatorRotate);

        bouncer.setInterpolator(interpolator);

        return bouncer;
    }


    public AnimatorSet getBottom2Right(View view) {

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "translationX", view.getTranslationX(), mapRectHeight / 2);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), -mapRectHeight / 2);
        ObjectAnimator animatorRotate = ObjectAnimator.ofFloat(view, "rotation", 90, 180);


        animatorX.setEvaluator(new CosEvaluator());
        animatorY.setEvaluator(new SinEvaluator());

        AnimatorSet bouncer = new AnimatorSet();
        bouncer.playTogether(animatorX, animatorY, animatorRotate);

        bouncer.setInterpolator(interpolator);

        return bouncer;
    }


    public ObjectAnimator getRight2Left(View view) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "translationX", view.getTranslationX(), view.getTranslationX() - mapRectHeight);
        animatorX.setInterpolator(interpolator);

        return animatorX;
    }

    public ObjectAnimator getLeft2Right(View view) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(view, "translationX", view.getTranslationX(), view.getTranslationX() + mapRectHeight);
        animatorX.setInterpolator(interpolator);

        return animatorX;
    }

    public ObjectAnimator getBottom2Top(View view) {
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), view.getTranslationY() - mapRectHeight);
        animatorY.setInterpolator(interpolator);

        return animatorY;
    }

    public ObjectAnimator getTop2Bottom(View view) {
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), view.getTranslationY() + mapRectHeight);
        animatorY.setInterpolator(interpolator);

        return animatorY;
    }


    class CosEvaluator extends FloatEvaluator {

        @Override
        public Float evaluate(float fraction, Number startValue, Number endValue) {
            double translation = endValue.floatValue() - endValue.doubleValue() * Math.cos(fraction * Math.PI / 2);
            translation += startValue.doubleValue();

            return (float) translation;
        }

    }


    class SinEvaluator extends FloatEvaluator {

        @Override
        public Float evaluate(float fraction, Number startValue, Number endValue) {
            double translation = endValue.doubleValue() * Math.sin(fraction * Math.PI / 2);
            translation += startValue.doubleValue();

            return (float) translation;
        }

    }
}
