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