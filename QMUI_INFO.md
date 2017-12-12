# BaseUIFrame #
[![](https://jitpack.io/v/24kpk/BaseUIFrame.svg)](https://jitpack.io/#24kpk/BaseUIFrame)

采用QMUI作为基础样式搭建的基础UI框架

 
## 使用步骤 ##
BaseUIFrame已经更新到[jitpack](https://jitpack.io/)上，使用AndroidStudio导入即可.

**Step 1** 在项目根目录 **build.gradle** 中添加

	allprojects {
	    repositories {
		    ....
		    maven { url "https://jitpack.io" }
	        }
	    }

**Step 2** 在App项目引用 **build.gradle** 中添加

	dependencies {
	        compile 'com.github.24kpk:BaseUIFrame:1.0.1'
	}

**Step 3** 样式引用

Application的Theme**继承BaseUIFrameAppTheme**

	<resources>
	
	    <!-- Base application theme. -->
	    <style name="AppTheme" parent="BaseUIFrameAppTheme">
	        <!-- Customize your theme here. -->
	        ......
	    </style>
	
	</resources>

**Step 4** 初始化

    public class YourApplication extends Application {
    
        @Override public void onCreate() {
        super.onCreate();
        
        /**
         * 默认配置
         * 内部调用了: initDir() initLog(false) initExceptionHandler()三个方法
         */
        BasicConfig.getInstance(this).init();
        
        or
        
        /**
         * 自定义配置
         * initDir() 初始化SDCard缓存目录
         * initLog() 初始化日志打印
         * initExceptionHandler() 初始化异常信息收集
         */
        BasicConfig.getInstance(this)
           .initDir() // or initDir(rootDirName)
           .initExceptionHandler()
           .initLog(true); 
        
        //其它初始化日志方法：
        /**
         * @param tag 日志标示
         */
        initLog(tag)
        
        /**
         * @param tag 日志标示
         * @param isDebug true:打印全部日志，false:不打印日志
         */
        initLog(tag, isDebug)
        
        /**
         * @param tag 日志标示，可以为空
         * @param methodCount 显示方法行数，默认为：2
         * @param isHideThreadInfo 是否显示线程信息，默认显示
         * @param adapter 自定义log输出
         * @param isDebug true:打印全部日志，false:不打印日志
         */
        initLog(tag, methodCount, isHideThreadInfo, adapter, isDebug)
        }
    }
    
    
## 基础组件 ##

### QMUIRoundButton ###

使按钮能方便地指定圆角、边框颜色、边框粗细、背景色

注意: 因为该控件的圆角采用 View 的 background 实现, 所以与原生的 <code>android:background</code> 有冲突。

- 如果在 xml 中用 <code>android:background</code> 指定 background, 该 background 不会生效。</li>
- 如果在该 View 构造完后用 `#setBackgroundResource(int)` 等方法设置背景, 该背景将覆盖圆角效果。</li>


如需在 xml 中指定圆角、边框颜色、边框粗细、背景色等值,采用 xml 属性 `com.qmuiteam.qmui.R.styleable#QMUIRoundButton`



圆角为高度的一半 

- `app:qmui_isRadiusAdjustBounds="true"`

指定圆角方向 

- `app:qmui_radiusTopLeft="8dp"` 
- `app:qmui_radiusTopRight="8dp"`
- `app:qmui_radiusBottomLeft="8dp"`
- `app:qmui_radiusBottomRight="8dp"`

示例代码

    <com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton
            android:id="@+id/test1"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerInParent="true"
            android:paddingBottom="10dp"
            android:paddingLeft="16dp"
            android:paddingRight="16dp"
            android:paddingTop="10dp"
            android:text="圆角为高度的一半"
            app:qmui_isRadiusAdjustBounds="true"/>
            
### QMUIDialog ###
消息类型对话框（蓝色按钮）

	new QMUIDialog.MessageDialogBuilder(mActivity)
	                .setTitle("标题")
	                .setMessage("确定要发送吗？")
	                .addAction("取消", new QMUIDialogAction.ActionListener() {
	                    @Override
	                    public void onClick(QMUIDialog dialog, int index) {
	                        dialog.dismiss();
	                    }
	                }).addAction("确定", new QMUIDialogAction.ActionListener() {
	                    @Override
	                    public void onClick(QMUIDialog dialog, int index) {
	                        ToastUtil.showToast("点击了确定按钮");
	                        dialog.dismiss();
	                    }
	                })
	                .show();
消息类型对话框（红色按钮）

	addAction(0,"删除",QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
	                    @Override
	                    public void onClick(QMUIDialog dialog, int index) {
	                        ToastUtil.showToast("点击了确定按钮");
	                        dialog.dismiss();
	                    }
	                })

菜单类型对话框 直接选择确认不带Check选项标识

	private void showMenuDialog() {
	        final String[] items = new String[]{"选项1", "选项2", "选项3"};
	        new QMUIDialog.MenuDialogBuilder(mActivity)
	                .addItems(items, new DialogInterface.OnClickListener() {
	                    @Override
	                    public void onClick(DialogInterface dialog, int which) {
	                        ToastUtil.showToast("你选择了 " + items[which]);
	                        dialog.dismiss();
	                    }
	                }).show();
	    }
带 Checkbox 的消息确认框

	private void showConfirmMessageDialog() {
	        new QMUIDialog.CheckBoxMessageDialogBuilder(mActivity)
	                .setTitle("退出后是否删除账号信息？")
	                .setMessage("删除账号信息")
	                .setChecked(true)
	                .addAction("取消", new QMUIDialogAction.ActionListener() {
	                    @Override
	                    public void onClick(QMUIDialog dialog, int index) {
	                        dialog.dismiss();
	                    }
	                }).addAction("退出", new QMUIDialogAction.ActionListener() {
	            @Override
	            public void onClick(QMUIDialog dialog, int index) {
	                ToastUtil.showToast("点击了退出按钮");
	                dialog.dismiss();
	            }
	        }).show();
	    }

单选菜单类型对话框 带选中标识

	private void showSingleChoiceDialog() {
	        final String[] items = new String[]{"选项1", "选项2", "选项3"};
	        final int checkedIndex = 0;
	        new QMUIDialog.CheckableDialogBuilder(mActivity)
	                .setCheckedIndex(checkedIndex)
	                .addItems(items, new DialogInterface.OnClickListener() {
	                    @Override
	                    public void onClick(DialogInterface dialog, int which) {
	                        ToastUtil.showToast("你选择了 " + items[which]);
	                        dialog.dismiss();
	                    }
	                }).show();
	    }
多选菜单类型对话框

    private void showMultiChoiceDialog() {
        final String[] items = new String[]{"选项1", "选项2", "选项3", "选项4", "选项5", "选项6", "选项7", "选项8"};
        final QMUIDialog.MultiCheckableDialogBuilder builder = new QMUIDialog.MultiCheckableDialogBuilder(mActivity)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.setCheckedItems(new int[]{1,3,5});
        builder.addAction("取消", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                dialog.dismiss();
            }
        });
        builder.addAction("确定", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                String info = "您选择了 ";
                for (int i : builder.getCheckedItemIndexes()) {
                    info += i+";";
                }
                ToastUtil.showToast(info);
                dialog.dismiss();
            }
        }).show();
    }

带输入框的对话框

	private void showEditTextDialog() {
	        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(mActivity);
	        builder.setTitle("标题")
	                .setPlaceholder("在此输入您的昵称")//HIDEN提示文字
	                .setInputType(InputType.TYPE_CLASS_TEXT)//输入框的键盘类型
	                .addAction("取消", new QMUIDialogAction.ActionListener() {
	                    @Override
	                    public void onClick(QMUIDialog dialog, int index) {
	                        dialog.dismiss();
	                    }
	                })
	                .addAction("确定", new QMUIDialogAction.ActionListener() {
	                    @Override
	                    public void onClick(QMUIDialog dialog, int index) {
	                        CharSequence text = builder.getEditText().getText();
	                        if (text != null && text.length() > 0) {
	                            ToastUtil.showToast("您的昵称: " + text);
	                            dialog.dismiss();
	                        } else {
	                            ToastUtil.showToast("请填入昵称" );
	                        }
	                    }
	                });
	        builder.show();
	    }

高度适应键盘升降的对话框

	QMUIKeyboardHelper.showKeyboard(autoTestDialogBuilder.getEditText(), true);

### QMUIEmptyView ###

	<com.qmuiteam.qmui.widget.QMUIEmptyView
	        android:id="@+id/emptyView"
	        android:layout_width="match_parent"
	        android:layout_height="match_parent"
	        android:layout_marginTop="?attr/qmui_topbar_height"
	        android:background="@color/qmui_config_color_white"
	        app:qmui_title_text="我是一个EmptyView"
	        app:qmui_detail_text="通过右上角的菜单来切换我的不同状态"/>

Java代码

		/**
	     * 显示emptyView
	     * @param loading 是否要显示loading
	     * @param titleText 标题的文字，不需要则传null
	     * @param detailText 详情文字，不需要则传null
	     * @param buttonText 按钮的文字，不需要按钮则传null
	     * @param onButtonClickListener 按钮的onClick监听，不需要则传null
	     */
	    public void show(boolean loading, String titleText, String detailText, String buttonText, OnClickListener onButtonClickListener) {
	        setLoadingShowing(loading);
	        setTitleText(titleText);
	        setDetailText(detailText);
	        setButton(buttonText, onButtonClickListener);
	        show();
	    }
### QMUIProgressBar ###
直线

	<com.qmuiteam.qmui.widget.QMUIProgressBar
	        android:id="@+id/rectProgressBar"
	        android:layout_width="match_parent"
	        android:layout_height="24dp"
	        android:textColor="@color/qmui_config_color_white"
	        android:textSize="16sp"
	        app:qmui_background_color="@color/qmui_config_color_gray_8"
	        app:qmui_progress_color="@color/app_color_blue_2"
	        app:qmui_type="type_rect"/>
圆环

	<com.qmuiteam.qmui.widget.QMUIProgressBar
	        android:id="@+id/circleProgressBar"
	        android:layout_width="250dp"
	        android:layout_height="250dp"
	        android:layout_marginTop="30dp"
	        android:textColor="?attr/qmui_config_color_gray_4"
	        android:textSize="22sp"
	        app:qmui_background_color="?attr/qmui_config_color_gray_8"
	        app:qmui_progress_color="@color/app_color_blue_2"
	        app:qmui_stroke_width="15dp"
	        app:qmui_type="type_circle"/>

设置文字

	mRectProgressBar.setQMUIProgressBarTextGenerator(new QMUIProgressBar.QMUIProgressBarTextGenerator() {
	            @Override
	            public String generateText(QMUIProgressBar progressBar, int value, int maxValue) {
	                return value + "/" + maxValue;
	            }
	        });

### QMUIGroupListView ###
通用的列表, 常用于 App 的设置界面

- 注意其父类不是 `android.widget.ListView`, 而是 `LinearLayout`, 一般需要在外层包多一个 `android.widget.ScrollView` 来支持滚动。

- 提供了 `Section` 的概念, 用来将列表分块。 具体见 `QMUIGroupListView.Section`


 * <pre>
 *         QMUIGroupListView groupListView = new QMUIGroupListView(context);
 *         // section 1
 *         QMUIGroupListView.newSection(context)
 *                 .setTitle("Section Title 1")
 *                 .setDescription("这是Section 1的描述")
 *                 .addItemView(groupListView.createItemView("item 1"), new OnClickListener() {
 *                     @Override
 *                     public void onClick(View v) {
 *                         Toast.makeText(context, "section 1 item 1", Toast.LENGTH_SHORT).show();
 *                     }
 *                 })
 *                 .addItemView(groupListView.createItemView("item 2"), new OnClickListener() {
 *                     @verride
 *                     public void onClick(View v) {
 *                         Toast.makeText(context, "section 1 item 2", Toast.LENGTH_SHORT).show();
 *                     }
 *                 })
 *                 // 设置分隔线的样式
 *                 .setSeparatorDrawableRes(
 *                         R.drawable.list_group_item_single_bg,
 *                         R.drawable.personal_list_group_item_top_bg,
 *                         R.drawable.list_group_item_bottom_bg,
 *                         R.drawable.personal_list_group_item_middle_bg)
 *                 // 如果没有title,加上默认title【Section n】
 *                 .setUseDefaultTitleIfNone(true)
 *                 // 默认使用TitleView的padding作section分隔,可以设置为false取消它
 *                 .setUseTitleViewForSectionSpace(false)
 *                 .addTo(groupListView);
 
 *         // section 2
 *         QMUIGroupListView.newSection(context)
 *                 .setTitle("Section Title 2")
 *                 .setDescription("这是Section 2的描述")
 *                 .addItemView(groupListView.createItemView("item 1"), new OnClickListener() {
 *                     @Override
 *                     public void onClick(View v) {
 *                         Toast.makeText(context, "section 2 item 1", Toast.LENGTH_SHORT).show();
 *                     }
 *                 })
 *                 .addItemView(groupListView.createItemView("item 2"), new OnClickListener() {
 *                     @Override
 *                     public void onClick(View v) {
 *                         Toast.makeText(context, "section 2 item 2", Toast.LENGTH_SHORT).show();
 *                     }
 *                 })
 *                 .addTo(groupListView);