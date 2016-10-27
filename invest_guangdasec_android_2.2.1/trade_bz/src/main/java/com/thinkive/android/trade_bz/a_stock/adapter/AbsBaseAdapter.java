package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 描述：通用适配器，需继承该适配器，实现onFillComponentData方法。
 * 该方法里实现listView的Item数据填充，如使用holder.getComponentById()方法再根据控件Id来
 * 获取指定控件的实例对象，然后你就可以随便对这个实例控件做什么都可以了。
 * 这个抽象适配器使用了泛型T,该T是指你需要传进来的某个bean类，这样使用者就不用担心简单而重复的代码带来的工作量了。
 * 你只需要关心如何给ListView的Item赋好值。
 * 该适配器自带有一种工具，就是异步下载图片并具有临时缓存功能，使用者只需要在onFillComponentData()方法或者子类其他地方里调用
 * loadBitmapToImageView()方法即可，使用这个方法需要传两个参数，一个是ImageView实例对象和下载地址。
 * </p>
 *
 * @author 黎丝军
 * @version 1.0
 * @corporation 深圳市思迪信息科技有限公司
 * @date 2015/5/26
 */
public abstract class AbsBaseAdapter<T> extends BaseAdapter {

    /**
     * 用于存储运行时必须的实例对象
     * 在此我使用了弱类型
     */
    WeakReference<Context> mContext = null;
    /**
     * 用于存储Item的布局Id
     */
    private int mItemLayoutId = 0;
    /**
     * 用于存储数据集
     */
    private ArrayList<T> mDataList = null;
    /**
     * 用于存储item里的图片
     */
    private Map<Object, Bitmap> mMapData = null;

    /**
     * 是否对listview进行界面复用
     */
    private boolean isReuseView = true;

    /**
     * 万能抽象器的构造器
     * 该构造器不需传列表需要的数据集
     * 只实现初始化，需要给ListView填充数据时，调用setListData()方法就可。
     *
     * @param context      运行时
     * @param itemLayoutId item布局Id
     */
    public AbsBaseAdapter(Context context, int itemLayoutId) {
        this(context, itemLayoutId, null);
    }

    /**
     * 字体变量
     */
    private Typeface mFontFace;

    /**
     * 万能抽象器的构造器
     * 该构造器需要使用者传一个列表数据集
     *
     * @param context      运行时
     * @param itemLayoutId item布局Id
     * @param dataList     数据集
     */
    public AbsBaseAdapter(Context context, int itemLayoutId, ArrayList<T> dataList) {
        mContext = new WeakReference<Context>(context);
        mItemLayoutId = itemLayoutId;
        if (dataList != null && !dataList.isEmpty()) {
            mDataList = dataList;
        } else {
            mDataList = new ArrayList<T>();
        }
        mMapData = new HashMap<Object, Bitmap>();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public T getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder = ViewHolder.getInstance(getContext(), convertView, null, mItemLayoutId, position, isReuseView);
        onFillComponentData(holder, getItem(position));
        return holder.getConvertView();
    }

    /**
     * 该方法用于给列表设置数据集
     *
     * @param listData 列表数据集
     */
    public void setListData(List<T> listData) {
        if (!mDataList.isEmpty()) {
            mDataList.clear();
        }
        mDataList.addAll(listData);
    }

    public ArrayList<T> getListData() {
        return mDataList;
    }

    /**
     * 该方法是抽象方法，是需要使用者自己去实现的
     * 在该方法里，使用者只需要使用holder实例对象调用getComponentById()方法
     * 根据布局里声明的Id来获取对应的控件，然后给这个控件用bean
     * 或者其他数据bean来给这个控件填充数据就是了。
     *
     * @param holder ViewHolder实例对象
     * @param bean   数据bean实例对象
     */
    protected abstract void onFillComponentData(ViewHolder holder, T bean);

    /**
     * 获取运行时实例对象
     *
     * @return 运行时实例对象
     */
    protected Context getContext() {
        return mContext.get();
    }

    /**
     * ViewHolder类是用来将adapter适配器里重复的代码抽取出来同时也是用作列表Item的复用，
     * 使用者在使用时只需要调用getInstance()方法来得到ViewHolder的一个实例对象，
     * 然后使用这个实例对象去操控具体的item里的视图组件，当然在这个方法里体现了Item的复用
     * 并也同时达到了重复代码的抽取。
     */
    protected static class ViewHolder {
        /**
         * 用于保存复用后真实的Item位置
         * 使之不然显示层混乱
         */
        private int mPosition = 0;
        public int getPosition() {
            return mPosition;
        }
        /**
         * 用于存储item布局视图控件
         */
        private View mConvertView = null;
        /**
         * 用于存储item里的视图组件对象
         */
        private SparseArray<View> mSparseData = null;

        /**
         * ViewHolder构造方法，该构造方法是私有的
         * 目的在于不让使用者直接通过new来申明对象来达到复用的效果
         *
         * @param context      运行时
         * @param parent       父控件
         * @param itemLayoutId item布局Id
         * @param position     item位置
         */
        private ViewHolder(Context context, ViewGroup parent, int itemLayoutId, int position) {
            mPosition = position;
            mSparseData = new SparseArray<View>();
            mConvertView = LayoutInflater.from(context).inflate(itemLayoutId, parent);
            mConvertView.setTag(this);
        }

        /**
         * 该方法是用来实例化ViewHolder对象的
         * 通过这个方法就可以实现item的复用和重复代码的抽取。
         *
         * @param context      运行时
         * @param convertView  item布局视图
         * @param parent       父视图
         * @param itemLayoutId item布局Id
         * @param position     item的位置
         * @return 返回ViewHolder的实例对象
         */
        public static ViewHolder getInstance(Context context, View convertView, ViewGroup parent, int itemLayoutId, int position, boolean isReuse) {
            if (convertView == null || !isReuse) {
                return new ViewHolder(context, parent, itemLayoutId, position);
            }
            final ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }

        /**
         * 该方法就是通过控件Id来获取对应控件的实例对象
         * 如果该控件Id对应的控件实例不存在，那么就通过findViewById()方法来创建
         * 这个视图对象，并存储到指定的列表里供下次复用，否则就直接获取指定列表的控件
         * 并返回。
         *
         * @param componentId 控件Id
         * @param <T>         泛指继承了View视图的子视图
         * @return 具体的视图实例对象
         */
        public <T extends View> T getComponentById(int componentId) {
            T component = (T) mSparseData.get(componentId);
            if (component == null) {
                component = (T) mConvertView.findViewById(componentId);
                mSparseData.put(componentId, component);
            }
            return component;
        }

        /**
         * 用于获取某个item的布局视图
         *
         * @return 取某个item的布局视图
         */
        public View getConvertView() {
            return mConvertView;
        }
    }

    /**
     * 向指定的ImageView导入图片
     * 根据图片地址，如果该图片已经存在那么就不下载
     * 否则下载图片并临时保存
     *
     * @param imageView     指定的ImageView的实例对象
     * @param imageUrl      图片下载url
     * @param defaultIconId 默认图片Id
     */
    protected void loadBitmapToImageView(ImageView imageView, Object imageUrl, int defaultIconId) {
        imageView.setImageResource(defaultIconId);
        final Bitmap bitmap = mMapData.get(imageUrl);
        if (bitmap == null) {
            //TODO 下载图片并保存到缓存里
            new AsyncDownload(imageView, imageUrl, defaultIconId).execute((String) imageUrl);
            return;
        }
        imageView.setImageBitmap(bitmap);
    }

    /**
     * <p>
     * 描述：用于适配器内部异步下载图片，如果对应地址的图片没有下载则下载
     * 下载完后，自动存储到临时缓存当中，供下次重复使用
     * </p>
     */
    private class AsyncDownload extends AsyncTask<String, Void, Bitmap> {

        /**
         * 用于存储下载下来的图片
         */
        private ImageView mImageView = null;
        /**
         * 用于保存对应下载地址的键值
         */
        private Object mKey = null;
        /**
         * 用于保存默认图片资源Id
         */
        private int mDefaultIconId = 0;

        /**
         * 构造函数，用于实例化异步下载对象
         *
         * @param imageView     ImageView实例
         * @param key           键值
         * @param defaultIconId 默认图片Id
         */
        public AsyncDownload(ImageView imageView, Object key, int defaultIconId) {
            mImageView = imageView;
            mKey = key;
            mDefaultIconId = defaultIconId;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
//            return ImageUtils.getBitmapFromUrl(params[0], 30 * 1000);
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                mMapData.put(mKey, bitmap);
                mImageView.setImageBitmap(bitmap);
                notifyDataSetChanged();
                return;
            }
            mImageView.setImageResource(mDefaultIconId);
        }
    }

    public void setIsReuseView(boolean isReuseView) {
        this.isReuseView = isReuseView;
    }
}
