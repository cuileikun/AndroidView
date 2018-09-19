package com.gitstudy.photoview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/** 
 * @ClassName: MatrixImageView 
 * @Description:  ���Ŵ���С���ƶ�Ч����ImageView
 * @author LinJ
 * @date 2015-1-7 ����11:15:07 
 *  
 */
public class MatrixImageView extends ImageView{
	public final static String TAG="MatrixImageView";
	private GestureDetector mGestureDetector;
	/**  ģ��Matrix�����Գ�ʼ�� */ 
	private  Matrix mMatrix=new Matrix();
	/**  ͼƬ����*/ 
	private float mImageWidth;
	/**  ͼƬ�߶� */ 
	private float mImageHeight;
	/**  ԭʼ���ż��� */ 
	private float mScale
	;
	private OnMovingListener moveListener;
	private OnSingleTapListener singleTapListener;

	public MatrixImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		MatrixTouchListener mListener=new MatrixTouchListener();
		setOnTouchListener(mListener);
		mGestureDetector=new GestureDetector(getContext(), new GestureListener(mListener));
		//��������Ϊbalck
	    setBackgroundColor(Color.BLACK);
		//��������������ΪCENTER_INSIDE����ʾ��ͼƬ������ʾ,���ҿ�����ֵΪ�ؼ����
		setScaleType(ScaleType.FIT_CENTER);
	}
	public MatrixImageView(Context context) {
		super(context, null);
		MatrixTouchListener mListener=new MatrixTouchListener();
		setOnTouchListener(mListener);
		mGestureDetector=new GestureDetector(getContext(), new GestureListener(mListener));
		//��������Ϊbalck
	    setBackgroundColor(Color.BLACK);
	  //��������������ΪCENTER_INSIDE����ʾ��ͼƬ������ʾ,���ҿ�����ֵΪ�ؼ����
		setScaleType(ScaleType.FIT_CENTER);	
	}
	public void setOnMovingListener(OnMovingListener listener){
		moveListener=listener;
	}
	public void setOnSingleTapListener(OnSingleTapListener onSingleTapListener) {
		this.singleTapListener = onSingleTapListener;
	}
	@Override
	public void setImageBitmap(Bitmap bm) {
		// TODO Auto-generated method stub
		super.setImageBitmap(bm);
		//��СΪ0 ��ʾ��ǰ�ؼ���Сδ����  ���ü�������  �ڻ���ǰ��ֵ
		if(getWidth()==0){
			ViewTreeObserver vto = getViewTreeObserver();   
			vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener()
			{
				public boolean onPreDraw()
				{
					initData();
					//��ֵ�������Ƴ��ü�������
					MatrixImageView.this.getViewTreeObserver().removeOnPreDrawListener(this);
					return true;
				}
			});
		}else {
			initData();
		}		
	}

	/**  
	 *   ��ʼ��ģ��Matrix��ͼƬ����������
	 */
	private void initData() {
		//������ͼƬ�󣬻�ȡ��ͼƬ������任����
		mMatrix.set(getImageMatrix());
		float[] values=new float[9];
		mMatrix.getValues(values);
		//ͼƬ���Ϊ��Ļ��ȳ����ű���
		mImageWidth=getWidth()/values[Matrix.MSCALE_X];
		mImageHeight=(getHeight()-values[Matrix.MTRANS_Y]*2)/values[Matrix.MSCALE_Y];
		mScale=values[Matrix.MSCALE_X];
	}

	public class MatrixTouchListener implements OnTouchListener{
		/** ������Ƭģʽ */
		private static final int MODE_DRAG = 1;
		/** �Ŵ���С��Ƭģʽ */
		private static final int MODE_ZOOM = 2;
		/**  ��֧��Matrix */ 
		private static final int MODE_UNABLE=3;
		/**   ������ż���*/ 
		float mMaxScale=6;
		/**   ˫��ʱ�����ż���*/ 
		float mDobleClickScale=2;
		private int mMode = 0;// 
		/**  ���ſ�ʼʱ����ָ��� */ 
		private float mStartDis;
		/**   ��ǰMatrix*/ 
		private Matrix mCurrentMatrix = new Matrix();	

		/** ���ڼ�¼��ʼʱ�������λ�� */

		/** ��ViewPager������أ��жϵ�ǰ�Ƿ�������ơ�����  */ 
		boolean mLeftDragable;
		boolean mRightDragable;
		/**  �Ƿ��һ���ƶ� */ 
		boolean mFirstMove=false;
		private PointF mStartPoint = new PointF();
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getActionMasked()) {
			case MotionEvent.ACTION_DOWN:
				//�����϶�ģʽ
				mMode=MODE_DRAG;
				mStartPoint.set(event.getX(), event.getY());
				isMatrixEnable();
				startDrag();
				checkDragable();
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				reSetMatrix();
				stopDrag();
				break;
			case MotionEvent.ACTION_MOVE:
				if (mMode == MODE_ZOOM) {
					setZoomMatrix(event);
				}else if (mMode==MODE_DRAG) {
					setDragMatrix(event);
				}else {
					stopDrag();
				}
				break;
			case MotionEvent.ACTION_POINTER_DOWN:
				if(mMode==MODE_UNABLE) return true;
				mMode=MODE_ZOOM;
				mStartDis = distance(event);
				break;
			case MotionEvent.ACTION_POINTER_UP:

				break;
			default:
				break;
			}
			return mGestureDetector.onTouchEvent(event);
		}

		/**  
		 *   �ӿؼ���ʼ�����ƶ�״̬����ViewPager�޷����ض��ӿؼ���Touch�¼�
		 */
		private void startDrag(){
			if(moveListener!=null) moveListener.startDrag();

		}
		/**  
		 *   �ӿؼ���ʼֹͣ�ƶ�״̬��ViewPager�����ض��ӿؼ���Touch�¼�
		 */
		private void stopDrag(){
			if(moveListener!=null) moveListener.stopDrag();
		}

		/**  
		 *   ���ݵ�ǰͼƬ���ұ�Ե���ÿ���ק״̬
		 */
		private void checkDragable() {
			mLeftDragable=true;
			mRightDragable=true;
			mFirstMove=true;
			float[] values=new float[9];
			getImageMatrix().getValues(values);
			//ͼƬ���Ե�뿪��߽磬��ʾ��������
			if(values[Matrix.MTRANS_X]>=0)
				mRightDragable=false;
			//ͼƬ�ұ�Ե�뿪�ұ߽磬��ʾ��������
			if((mImageWidth)*values[Matrix.MSCALE_X]+values[Matrix.MTRANS_X]<=getWidth()){
				mLeftDragable=false;
			}
		}

		/**  
		 *  ������ק״̬�µ�Matrix
		 *  @param event   
		 */
		public void setDragMatrix(MotionEvent event) {
			if(isZoomChanged()){
				float dx = event.getX() - mStartPoint.x; // �õ�x����ƶ�����
				float dy = event.getY() - mStartPoint.y; // �õ�x����ƶ�����
				//�����˫����ͻ,����10f�������϶�
				if(Math.sqrt(dx*dx+dy*dy)>10f){	
					mStartPoint.set(event.getX(), event.getY());
					//�ڵ�ǰ�������ƶ�
					mCurrentMatrix.set(getImageMatrix());
					float[] values=new float[9];
					mCurrentMatrix.getValues(values);
					dy=checkDyBound(values,dy);	
					dx=checkDxBound(values,dx,dy);

					mCurrentMatrix.postTranslate(dx, dy);
					setImageMatrix(mCurrentMatrix);
				}
			}else {
				stopDrag();
			}
		}

		/**  
		 *  �ж����ż����Ƿ��Ǹı��
		 *  @return   true��ʾ�ǳ�ʼֵ,false��ʾ��ʼֵ
		 */
		private boolean isZoomChanged() {
			float[] values=new float[9];
			getImageMatrix().getValues(values);
			//��ȡ��ǰX�����ż���
			float scale=values[Matrix.MSCALE_X];
			//��ȡģ���X�����ż����������Ƚ�
			return scale!=mScale;
		}

		/**  
		 *  �͵�ǰ����Աȣ�����dy��ʹͼ���ƶ��󲻻ᳬ��ImageView�߽�
		 *  @param values
		 *  @param dy
		 *  @return   
		 */
		private float checkDyBound(float[] values, float dy) {
			float height=getHeight();
			if(mImageHeight*values[Matrix.MSCALE_Y]<height)
				return 0;
			if(values[Matrix.MTRANS_Y]+dy>0)
				dy=-values[Matrix.MTRANS_Y];
			else if(values[Matrix.MTRANS_Y]+dy<-(mImageHeight*values[Matrix.MSCALE_Y]-height))
				dy=-(mImageHeight*values[Matrix.MSCALE_Y]-height)-values[Matrix.MTRANS_Y];
			return dy;
		}

		/**  
		 *  �͵�ǰ����Աȣ�����dx��ʹͼ���ƶ��󲻻ᳬ��ImageView�߽�
		 *  @param values
		 *  @param dx
		 *  @return   
		 */
		private float checkDxBound(float[] values,float dx,float dy) {
			float width=getWidth();
			if(!mLeftDragable&&dx<0){
				//�����y��ĶԱȣ���ʾ�ڼ�������ֱ���������ʱ���л�Item
				if(Math.abs(dx)*0.4f>Math.abs(dy)&&mFirstMove){
					stopDrag();
				}
				return 0;
			}
			if(!mRightDragable&&dx>0){
				//�����y��ĶԱȣ���ʾ�ڼ�������ֱ���������ʱ���л�Item
				if(Math.abs(dx)*0.4f>Math.abs(dy)&&mFirstMove){
					stopDrag();
				}
				return 0;
			}
			mLeftDragable=true;
			mRightDragable=true;
			if(mFirstMove) mFirstMove=false;
			if(mImageWidth*values[Matrix.MSCALE_X]<width){
				return 0;

			}
			if(values[Matrix.MTRANS_X]+dx>0){
				dx=-values[Matrix.MTRANS_X];
			}
			else if(values[Matrix.MTRANS_X]+dx<-(mImageWidth*values[Matrix.MSCALE_X]-width)){
				dx=-(mImageWidth*values[Matrix.MSCALE_X]-width)-values[Matrix.MTRANS_X];
			}
			return dx;
		}

		/**  
		 *  ��������Matrix
		 *  @param event   
		 */
		private void setZoomMatrix(MotionEvent event) {
			//ֻ��ͬʱ�����������ʱ���ִ��
			if(event.getPointerCount()<2) return;
			float endDis = distance(event);// ��������
			if (endDis > 10f) { // ������ָ��£��һ���ʱ�����ش���10
				float scale = endDis / mStartDis;// �õ����ű���
				mStartDis=endDis;//���þ���
				mCurrentMatrix.set(getImageMatrix());//��ʼ��Matrix
				float[] values=new float[9];
				mCurrentMatrix.getValues(values);
				scale = checkMaxScale(scale, values);
				PointF centerF=getCenter(scale,values);
				mCurrentMatrix.postScale(scale, scale,centerF.x,centerF.y);
				setImageMatrix(mCurrentMatrix);	
			}
		}

		/**  
		 *  ��ȡ���ŵ����ĵ㡣
		 *  @param scale
		 *  @param values
		 *  @return   
		 */
		private PointF getCenter(float scale,float[] values) {
			//���ż���С��ԭʼ���ż���ʱ����Ϊ�Ŵ�״̬ʱ������ImageView���ĵ���Ϊ�������ĵ�
			if(scale*values[Matrix.MSCALE_X]<mScale||scale>=1){
				return new PointF(getWidth()/2,getHeight()/2);
			}
			float cx=getWidth()/2;
			float cy=getHeight()/2;
			//��ImageView���ĵ�Ϊ�������ģ��ж����ź��ͼƬ���Ե�Ƿ���뿪ImageView���Ե���ǵĻ������ԵΪX������
			if((getWidth()/2-values[Matrix.MTRANS_X])*scale<getWidth()/2)
				cx=0;
			//�ж����ź���ұ�Ե�Ƿ���뿪ImageView�ұ�Ե���ǵĻ����ұ�ԵΪX������
			if((mImageWidth*values[Matrix.MSCALE_X]+values[Matrix.MTRANS_X])*scale<getWidth())
				cx=getWidth();
			return new PointF(cx,cy);
		}

		/**  
		 *  ����scale��ʹͼ�����ź󲻻ᳬ�������
		 *  @param scale
		 *  @param values
		 *  @return   
		 */
		private float checkMaxScale(float scale, float[] values) {
			if(scale*values[Matrix.MSCALE_X]>mMaxScale) 
				scale=mMaxScale/values[Matrix.MSCALE_X];
			return scale;
		}

		/**  
		 *   ����Matrix
		 */
		private void reSetMatrix() {
			if(checkRest()){
				mCurrentMatrix.set(mMatrix);
				setImageMatrix(mCurrentMatrix);
			}else {
				//�ж�Y���Ƿ���Ҫ����
				float[] values=new float[9];
				getImageMatrix().getValues(values);
				float height=mImageHeight*values[Matrix.MSCALE_Y];
				if(height<getHeight()){
					//��ͼƬ��ʵ�߶�С�������߶�ʱ��Y����У�Y������ƫ����Ϊ���߸߶Ȳ�/2��
					float topMargin=(getHeight()-height)/2;
					if(topMargin!=values[Matrix.MTRANS_Y]){
						mCurrentMatrix.set(getImageMatrix());
						mCurrentMatrix.postTranslate(0, topMargin-values[Matrix.MTRANS_Y]);
						setImageMatrix(mCurrentMatrix);
					}
				}
			}
		}

		/**  
		 *  �ж��Ƿ���Ҫ����
		 *  @return  ��ǰ���ż���С��ģ�����ż���ʱ������ 
		 */
		private boolean checkRest() {
			// TODO Auto-generated method stub
			float[] values=new float[9];
			getImageMatrix().getValues(values);
			//��ȡ��ǰX�����ż���
			float scale=values[Matrix.MSCALE_X];
			//��ȡģ���X�����ż����������Ƚ�
			return scale<mScale;
		}

		/**  
		 *  �ж��Ƿ�֧��Matrix
		 */
		private void isMatrixEnable() {
			//�����س���ʱ����������
			if(getScaleType()!=ScaleType.CENTER){
				setScaleType(ScaleType.MATRIX);
			}else {
				mMode=MODE_UNABLE;//����Ϊ��֧������
			}
		}

		/**  
		 *  ����������ָ��ľ���
		 *  @param event
		 *  @return   
		 */
		private float distance(MotionEvent event) {
			float dx = event.getX(1) - event.getX(0);
			float dy = event.getY(1) - event.getY(0);
			/** ʹ�ù��ɶ���������֮��ľ��� */
			return (float) Math.sqrt(dx * dx + dy * dy);
		}

		/**  
		 *   ˫��ʱ����
		 */
		public void onDoubleClick(){
			float scale=isZoomChanged()?1:mDobleClickScale;
			mCurrentMatrix.set(mMatrix);//��ʼ��Matrix
			mCurrentMatrix.postScale(scale, scale,getWidth()/2,getHeight()/2);	
			setImageMatrix(mCurrentMatrix);
		}
	}


	private class  GestureListener extends SimpleOnGestureListener{
		private final MatrixTouchListener listener;
		public GestureListener(MatrixTouchListener listener) {
			this.listener=listener;
		}
		@Override
		public boolean onDown(MotionEvent e) {
			//����Down�¼�
			return true;
		}
		@Override
		public boolean onDoubleTap(MotionEvent e) {
			//����˫���¼�
			listener.onDoubleClick();
			return true;
		}
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// TODO Auto-generated method stub
			return super.onSingleTapUp(e);
		}

		@Override
		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub
			super.onLongPress(e);
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			return super.onScroll(e1, e2, distanceX, distanceY);
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			return super.onFling(e1, e2, velocityX, velocityY);
		}

		@Override
		public void onShowPress(MotionEvent e) {
			// TODO Auto-generated method stub
			super.onShowPress(e);
		}

		@Override
		public boolean onDoubleTapEvent(MotionEvent e) {
			// TODO Auto-generated method stub
			return super.onDoubleTapEvent(e);
		}

		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			if(singleTapListener!=null) singleTapListener.onSingleTap();
			return super.onSingleTapConfirmed(e);
		}

	}
	/** 
	 * @ClassName: OnChildMovingListener 
	 * @Description:  MatrixImageView�ƶ������ӿ�,������֯ViewPager��Move����������
	 * @author LinJ
	 * @date 2015-1-12 ����4:39:32 
	 *  
	 */
	public interface OnMovingListener{
		public void  startDrag();
		public void  stopDrag();
	}

	/** 
	 * @ClassName: OnSingleTapListener 
	 * @Description:  ����ViewPager��Ļ�����¼��������Ǽ����ӿؼ�MatrixImageView�ĵ����¼�
	 * @author LinJ
	 * @date 2015-1-12 ����4:48:52 
	 *  
	 */
	public interface OnSingleTapListener{
		public void onSingleTap();
	}
}
