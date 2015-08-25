package com.wk.cmbl.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.wk.cmbl.R;

import org.kymjs.kjframe.KJBitmap;

import java.util.Random;

/**
 * 随机播放设定的动画
 * Created by Administrator on 2015/8/18 0018.
 */
public class KenBurns implements Animator.AnimatorListener {
    /** 放大1.5倍 */
    private final int ANIM_SCALE = 0;
    /** 透明出从1降到0 */
    private final int ANIM_ALPHA = 1;
    /** 先放大1.5倍，在向右平移100 */
    private final int ANIM_SCALE_LEFT = 2;
    /** 先从0.5倍放大到1.5倍，在向下平移100 */
    private final int ANIM_SCALE_DOWN = 3;
    /** 以中心点为中心轴边旋转360边缩小 */
    private final int ANIM_ROTATION_CENTER = 4;
    /** 以X轴为中心轴边旋转360边缩小 */
    private final int ANIM_ROTATION_X = 5;
    /** 以Y轴为中心轴边旋转360边缩小 */
    private final int ANIM_ROTATION_Y = 6;
    /** 单个动画总播放时长 */
    private final int MAX_ANIM_DURATION = 3000;
    /** 两部动画单部播放时长 */
    private final int HALF_ANIM_DURATION = 1500;
    /***
     * 上下文对象
     */
    private Context context;
    /***
     * 外层框架布局控件
     */
    private FrameLayout layout;
    /***
     * 图片源
     */
    private int[] resIds = {R.drawable.img_1, R.drawable.img_2, R.drawable.img_3,
            R.drawable.img_4, R.drawable.img_5};
    /***
     * 图片源
     */
    private String[] resUrl = null;
    /***
     * 播放的动画类
     */
    private AnimatorSet anim;
    /***
     * 用来判断是否正在播放
     */
    private boolean playing = true;
    /***
     * 播放动画的时长
     */
    private int animDuration = 3000;
    /***
     * 图片加载类
     */
    private KJBitmap kjb;

    public KenBurns(Context context) {
        this.context = context;
        kjb = new KJBitmap();
    }

    /***
     * 设置图片源
     * @param resIds
     */
    public void setResIds(int[] resIds) {
        this.resIds = resIds;
    }

    /***
     * 设置图片源
     * @param resUrl
     */
    public void setResUrl(String[] resUrl) {
        this.resUrl = resUrl;
    }

    /***
     * 设置外层框架布局
     * @param layout
     */
    public void setLayout(FrameLayout layout) {
        this.layout = layout;
    }

    /***
     * 获取是否正则播放动画
     * @return
     */
    public boolean isPlaying() {
        return playing;
    }

    /***
     * 获取一个宽度和高度都是match的imageview
     * @param resId 图片源
     * @return
     */
    public ImageView getNewView(int resId) {
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(resId);
        return imageView;
    }

    /***
     * 获取一个宽度和高度都是match的imageview
     * @param resUrl 图片源
     * @return
     */
    public ImageView getNewView(String resUrl) {
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        kjb.display(imageView, resUrl);
        return imageView;
    }

    /***
     * 开始播放下一个动画
     * @param view
     */
    public void nextAnimation(View view) {
        anim = new AnimatorSet();
        final int index = new Random().nextInt(7);
//        final int index = 4;
        switch (index) {
            case ANIM_SCALE:// 放大1.5倍
                animDuration = MAX_ANIM_DURATION;
                anim.playTogether(
                        ObjectAnimator.ofFloat(view, "scaleX", 1.5f),
                        ObjectAnimator.ofFloat(view, "scaleY", 1.5f));
                break;
            case ANIM_ALPHA:// 透明出从1降到0
                animDuration = MAX_ANIM_DURATION;
                anim.play(ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f));
                break;
            case ANIM_SCALE_LEFT:// 先放大1.5倍，在向右平移100
                animDuration = HALF_ANIM_DURATION;
                anim.playTogether(
                        ObjectAnimator.ofFloat(view, "scaleX", 1.5f),
                        ObjectAnimator.ofFloat(view, "scaleY", 1.5f));
                anim.play(ObjectAnimator.ofFloat(view, "translationX", 0f, 100f)).after(HALF_ANIM_DURATION);
                break;
            case ANIM_SCALE_DOWN:// 先从0.5倍放大到1.5倍，在向下平移100
                animDuration = HALF_ANIM_DURATION;
                anim.playTogether(
                        ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1.5f),
                        ObjectAnimator.ofFloat(view, "scaleY", 0.5f, 1.5f));
                anim.play(ObjectAnimator.ofFloat(view, "translationY", 0f, 100f)).after(HALF_ANIM_DURATION);
                break;
            case ANIM_ROTATION_CENTER:// 以中心点为中心轴边旋转360边缩小
                animDuration = MAX_ANIM_DURATION;
                anim.playTogether(
                        ObjectAnimator.ofFloat(view, "scaleX", 0.5f),
                        ObjectAnimator.ofFloat(view, "scaleY", 0.5f),
                        ObjectAnimator.ofFloat(view, "rotation", 0.0f, 360.0f));
                break;
            case ANIM_ROTATION_X:// 以X轴为中心轴边旋转360边缩小
                animDuration = MAX_ANIM_DURATION;
                anim.playTogether(
                        ObjectAnimator.ofFloat(view, "scaleX", 0.5f),
                        ObjectAnimator.ofFloat(view, "scaleY", 0.5f),
                        ObjectAnimator.ofFloat(view, "rotationX", 0.0f, 360.0f));
                break;
            case ANIM_ROTATION_Y:// 以Y轴为中心轴边旋转360边缩小
                animDuration = MAX_ANIM_DURATION;
                anim.playTogether(
                        ObjectAnimator.ofFloat(view, "scaleX", 0.5f),
                        ObjectAnimator.ofFloat(view, "scaleY", 0.5f),
                        ObjectAnimator.ofFloat(view, "rotationY", 0.0f, 360.0f));
                break;
            default:
                animDuration = MAX_ANIM_DURATION;
                anim.playTogether(
                        ObjectAnimator.ofFloat(view, "scaleX", 0.5f),
                        ObjectAnimator.ofFloat(view, "scaleY", 0.5f),
                        ObjectAnimator.ofFloat(view, "rotationY", 0.0f, 360.0f));
                break;
        }
        anim.setDuration(animDuration);
        anim.addListener(this);
        anim.start();
    }

    /***
     * 停止播放动画
     */
    public void stopAnimation() {
        if (playing) {
            playing = false;
            anim.cancel();
        }
    }

    /***
     * 开始播放动画
     */
    public void startAnimation() {
        if (!playing) {
            playing = true;
            anim.start();
        }
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (playing) {
            layout.removeAllViews();
            View view;
            if (resUrl != null)
                view = getNewView(resUrl[new Random().nextInt(resUrl.length)]);
            else
                view = getNewView(resIds[new Random().nextInt(resIds.length)]);
            layout.addView(view);

            nextAnimation(view);
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
