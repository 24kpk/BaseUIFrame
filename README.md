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
注：部分组件未理解到位 [详见](https://github.com/24kpk/BaseUIFrame/blob/master/QMUI_INFO.md)




























 
