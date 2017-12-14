# BaseUIFrame #

@EDITOR BY 冷寒


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
	        compile 'com.github.24kpk:BaseUIFrame:1.0.3'
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
    




# 附 [QMUI组件功能列表](http://qmuiteam.com/android/page/document.html)
## 功能特性
**全局 UI 配置**：只需要修改一份配置表就可以调整 App 的全局样式，包括组件颜色、导航栏、对话框、列表等。一处修改，全局生效。

**丰富的 UI 控件**：提供丰富常用的 UI 控件，例如 BottomSheet、Tab、圆角 ImageView、下拉刷新等，使用方便灵活，并且支持自定义控件的样式。

**高效的工具方法**：提供高效的工具方法，包括设备信息、屏幕信息、键盘管理、状态栏管理等，可以解决各种常见场景并大幅度提升开发效率。

如本项目配置的全局样式：
<resources>

    <!-- Base application theme. -->
    <style name="BaseUIFrameAppTheme" parent="QMUI.Compat">
        <!--默认关闭ActionBar、Title-->
        <item name="windowActionBar">false</item>
        <item name="windowNoTitle">true</item>

        <!-- 配置Android提供的theme -->
        <item name="android:textAppearanceListItemSmall">@style/QDTextAppearanceListItemSmall</item>
        <item name="android:textAppearanceListItem">@style/QDtextAppearanceListItem</item>
        <item name="android:listPreferredItemHeight">?attr/qmui_list_item_height_higher</item>
        <item name="android:listPreferredItemHeightSmall">?attr/qmui_list_item_height</item>

        <!-- 配置qmui提供的theme -->
        <item name="qmui_config_color_blue">@color/app_color_blue</item>
        <item name="qmui_topbar_title_color">@color/qmui_config_color_white</item>
        <item name="qmui_topbar_subtitle_color">@color/qmui_config_color_white</item>
        <item name="qmui_topbar_text_btn_color_state_list">@drawable/s_topbar_btn_color</item>
        <item name="qmui_topbar_height">48dp</item>
        <item name="qmui_topbar_image_btn_height">48dp</item>
        <item name="qmui_round_btn_bg_color">@drawable/s_btn_blue_bg</item>
        <item name="qmui_round_btn_border_color">@drawable/s_btn_blue_border</item>
        <item name="qmui_round_btn_text_color">@drawable/s_btn_blue_text</item>
        <item name="qmui_content_spacing_horizontal">20dp</item>
        <item name="qmui_content_padding_horizontal">@dimen/qmui_content_spacing_horizontal</item>

        <item name="QMUITopBarStyle">@style/QDTopBar</item>


        <!-- 配置app自己的theme -->
        <item name="app_primary_color">?attr/qmui_config_color_blue</item>
        <item name="app_content_bg_color">@color/qmui_config_color_white</item>
    </style>


    <style name="QDtextAppearanceListItem">
        <item name="android:textColor">?attr/qmui_config_color_black</item>
        <item name="android:textSize">18sp</item>
        <item name="android:background">?attr/qmui_s_list_item_bg_with_border_bottom_inset_left
        </item>
    </style>

    <style name="QDTextAppearanceListItemSmall">
        <item name="android:textColor">?attr/qmui_config_color_gray_4</item>
        <item name="android:textSize">16sp</item>
        <item name="android:background">?attr/qmui_s_list_item_bg_with_border_bottom_inset_left
        </item>
    </style>

    <style name="QDCommonTitle">
        <item name="android:layout_width">match_parent</item>
        <item name="android:layout_height">wrap_content</item>
        <item name="android:layout_marginBottom">6dp</item>
        <item name="android:textColor">?attr/qmui_config_color_gray_1</item>
        <item name="android:textSize">16sp</item>
    </style>

    <style name="QDCommonDescription">
        <item name="android:layout_width">match_parent</item>
        <item name="android:layout_height">wrap_content</item>
        <item name="android:layout_marginBottom">20dp</item>
        <item name="android:gravity">center</item>
        <item name="android:textColor">@color/app_color_description</item>
        <item name="android:textSize">15sp</item>
    </style>

    <style name="QDTopBar" parent="QMUI.TopBar">
        <item name="qmui_topbar_bg_color">?attr/app_primary_color</item>
    </style>

    <style name="QDRadiusButton" parent="@style/Button">
        <item name="android:layout_height">40dp</item>
        <item name="android:layout_width">wrap_content</item>
        <item name="android:paddingLeft">?attr/qmui_content_spacing_horizontal</item>
        <item name="android:paddingRight">?attr/qmui_content_spacing_horizontal</item>
        <item name="android:background">@drawable/s_radius_button_bg</item>
        <item name="android:textColor">@drawable/s_app_color_blue_2</item>
        <item name="android:textSize">14sp</item>
        <item name="android:gravity">center</item>
    </style>

    <style name="button_wrapper_style">
        <item name="android:layout_width">match_parent</item>
        <item name="android:layout_height">wrap_content</item>
        <item name="android:paddingTop">24dp</item>
        <item name="android:paddingBottom">24dp</item>
        <item name="android:background">@drawable/qmui_divider_bottom_bitmap</item>
    </style>

</resources>





注：部分组件未理解到位 [详见](https://github.com/24kpk/BaseUIFrame/blob/master/QMUI_INFO.md)




























 
