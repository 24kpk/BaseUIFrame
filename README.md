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
    




# 附QMUI 组件功能列表

### QMUIAnimationListView

使 `ListView` 支持添加/删除 Item 的动画，支持自定义动画效果。

### QMUIBottomSheet
在 `Dialog` 的基础上重新定制了 `show()` 和 `hide()` 时的动画效果, 使 `Dialog` 在界面底部升起和降下。提供了以下两个面板样式：

1.  列表样式：使用 `QMUIBottomSheet.BottomListSheetBuilder` 生成。
2.  宫格类型：使用 `QMUIBottomSheet.BottomGridSheetBuilder` 生成。


### QMUICommonListItemView
用作通用列表 `QMUIGroupListView` 里的 Item，也可单独使用。支持以下样式：

1.  展示一行文字。
2.  在右侧或下方增加一行说明文字。
3.  在 item 右侧显示一个开关或箭头或自定义的View


### QMUIDialog
提供了一系列常用的对话框，解决了使用系统默认对话框时在不同 Android 版本上的表现不一致的问题。使用不同的 Builder 来构建不同类型的对话框，这些 Builder 都拥有设置 title 和添加底部按钮的功能，不同的 Builder 特有的作用如下：

1.  `MessageDialogBuilder`: 消息类型的对话框 Builder。通过它可以生成一个带标题、文本消息、按钮的对话框。
2.  `ConfirmMessageDialogBuilder`: 带 Checkbox 的消息确认框 Builder。
3.  `EditTextDialogBuilder`: 带输入框的对话框 Builder。
4.  `MenuDialogBuilder`: 菜单对话框 Builder。
5.  `CheckableDialogBuilder`: 单选类型的对话框 Builder。
6.  `MultiCheckableDialogBuilder`: 多选类型的对话框 Builder。
7.  `CustomDialogBuilder`: 自定义对话框内容区域的 Builder。
8.  `AutoResizeDialogBuilder`: 随键盘升降自动调整 `Dialog` 高度的 Builder

### QMUIEmptyView
通用的空界面控件，支持显示 loading、主标题和副标题、图片。 

### QMUIFloatLayout
类似 CSS 里 `float: left` 的浮动布局，从左到右排列子 View 并自动换行。支持以下特性：

1.  控制子 `View` 之间的垂直/水平间距。
2.  控制子 `View` 的水平对齐方向（左对齐/居中/右对齐）。
3.  限制子 `View` 的个数或行数。

### QMUIFontFitTextView
使 `TextView` 在宽度固定的情况下，文字多到一行放不下时能缩小文字大小来自适应。

### QMUIGroupListView
通用的列表，常用于 App 的设置界面，注意其父类不是 `ListView` 而是 `LinearLayout`，所以一般要配合 `ScrollView` 使用。提供了 Section 的概念，用来将列表分块。
配合 `QMUIGroupListView.Section`, `QMUICommonListItemView` 和 `QMUIGroupListSectionHeaderFooterView` 使用。

### QMUIGroupListSectionHeaderFooterView</dt>
用作通用列表 `QMUIGroupListView` 里每个 Section 的头部或尾部，也可单独使用。

### QMUIItemViewsAdapter
一个带 cache 功能的“列表型数据-View”的适配器，适用于自定义 `View` 需要显示重复单元 `ListView` 的情景，cache 功能主要是保证在需要多次刷新数据或布局的情况下（`ListView` 或 `RecycleView` 的 itemView）复用已存在的 `View`。QMUI 用于 `QMUITabSegment` 中 `Tab` 与数据的适配。

### QMUIKeyboardHelper</dt>
提供更加便捷的方式针对给定的 EditText 显示/隐藏软键盘，并且提供了工具方法判断键盘是否当前可见。

### QMUILinkTextView</dt>
使 `TextView` 能自动识别 URL、电话、邮箱地址，相比 `TextView` 有以下特点：

1.  可以设置链接的样式。
2.  可以设置链接的点击事件。

### QMUILoadingView</dt>
用于显示 Loading 的 `View`，支持颜色和大小的设置。

### QMUIObservableScrollView</dt>
可以监听滚动事件的 `ScrollView`，并能在滚动回调中获取每次滚动前后的偏移量。

### QMUIPopup</dt>
提供一个浮层，支持自定义浮层的内容，支持在指定 `View` 的任一方向旁边展示该浮层，支持自定义浮层出现/消失的动画。

### QMUIListPopup</dt>
继承自 `QMUIPopup`，在 `QMUIPopup` 的基础上，支持显示一个列表。

### QMUIProgressBar</dt>
一个进度条控件，通过颜色变化显示进度，支持环形和矩形两种形式，主要特性如下：

1.  支持在进度条中以文字形式显示进度，支持修改文字的颜色和大小。
2.  可以通过 `xml` 属性修改进度背景色，当前进度颜色，进度条尺寸。
3.  支持限制进度的最大值。
      

### QMUIPullRefreshLayout</dt>
下拉刷新控件。支持自定义 RefreshView（表示正在刷新的 View），触发刷新的位置等特性。


### QMUIQQFaceView</dt>


1.  支持显示表情的伪 `TextView`（继续自定义 View,而不是真正的 `TextView`), 实现了 `TextView` 的 `maxLine`、`ellipsize`、`textSize`、`textColor` 等基本功能。
2.  支持与 `QMUITouchableSpan` 配合使用实现内容可点击。


### QMUIQQFaceCompiler</dt>
`QMUIQQFaceView` 的内容解析器，将文本内容解析成 `QMUIQQFaceView` 想要的数据格式。

### IQMUIQQFaceManager</dt>
`QMUIQQFaceView` 资源管理接口，使用 `QMUIQQFaceView` 必须实现这个接口以提供表情资源。

### QMUIRadiusImageView</dt>
提供为图片添加圆角、边框、剪裁到圆形或其他形状等功能。

### QMUIRoundButton</dt>
对 `Button` 提供圆角功能，支持以下特性：

1.  指定圆角的大小。
2.  分别指定不同方向的圆角大小。
3.  指定圆角的大小为高度的一半，并跟随高度变化自适应圆角大小。
4.  支持分别指定背景色和边框色，指定颜色时支持使用 color 或 `ColorStateList`。

### QMUIRoundButtonDrawable</dt>
使用该 `Drawable` 可以方便地生成圆角矩形/圆形 `Drawable`，提供设置背景色、描边大小和颜色、圆角自适应 View 高度等特性。

### QMUISpanTouchFixTextView</dt>
相比 `TextView`，修正了两个常见问题：

1.  修正了 `TextView` 与 `ClickableSpan` 一起使用时，点击 `ClickableSpan` 也会触发 `TextView` 的事件的问题。
2.  修正了 `TextView` 默认情况下如果添加了 `ClickableSpan` 之后就无法把点击事件传递给 `TextView` 的 Parent 的问题。
      

### QMUITabSegment</dt>
用于横向多个 `Tab` 的布局，包含多个特性：

1.  可以用 `xml` 或 `QMUITabSegment` 提供的 set 方法统一配置文字颜色、icon 位置、是否要下划线等。
2.  每个 `Tab` 都可以非常灵活的配置，内容上支持文字和 icon 的显示，icon 支持选中态，支持内容的排版对齐方向设置，支持显示红点，支持插入自定义的 `View`，支持监听双击事件等。
3.  可以通过 `setupWithViewPager(ViewPager)` 方法与 `ViewPager` 绑定。
      

### QMUITipDialog</dt>
提供一个浮层展示在屏幕中间，提供了以下两种样式：

1.  使用 `QMUITipDialog.Builder` 生成，提供了一个图标和一行文字的样式, 其中图标有 Loading、成功、失败等类型可选。
2.  使用 `QMUITipDialog.CustomBuilder` 生成，支持传入自定义的 `layoutResId`。
      

### QMUITopBar</dt>
通用的顶部 Bar。提供了以下功能：

1.  在左侧/右侧添加图片按钮/文字按钮/自定义View。
2.  设置标题/副标题，且支持设置标题/副标题的水平对齐方式。
      

### QMUITopBarLayout</dt>
对 `QMUITopBar` 的包裹类，并代理了 `QMUITopBar` 的方法。配合 `QMUIWindowInsetLayout` 使用，可使 `QMUITopBar` 在支持沉浸式状态栏的界面中顶部延伸到状态栏。

### QMUIVerticalTextView</dt>
在 `TextView` 的基础上支持文字竖排。

### QMUITouchableSpan</dt>
继承自 `ClickableSpan`，支持 normal 态和 press 态时有不同的背景颜色以及字体颜色。建议配合 `QMUISpanTouchFixTextView` 或其子类使用，便于事件传递的协调。

### QMUIWindowInsetLayout</dt>
配合沉浸式状态栏使用，用于协调子 `View` 的 `fitSystemWindows`。

### QMUIWrapContentListView</dt>
支持高度值为 `wrap_content` 的 `ListView`，解决原生 `ListView` 在设置高度为 `wrap_content` 时高度计算错误的 bug。

### QMUIBlockSpaceSpan</dt>
通过在段落之间设置该 span，实现段间距的效果。

### QMUICustomTypefaceSpan</dt>
支持以 `Typeface` 的方式设置 `span` 的字体，实现自定义字体的效果。

### QMUIAlignMiddleImageSpan</dt>
继承自 `ImageSpan`，在此基础上实现让 `span` 垂直居中的效果。

### QMUIMarginImageSpan</dt>
继承自 `QMUIMarginImageSpan`，在此基础上支持设置图片的左右间距。

### QMUITextSizeSpan</dt>
支持调整字体大小的 `span`。`AbsoluteSizeSpan` 可以调整字体大小，但在中英文混排下由于decent的不同，无法根据具体需求进行底部对齐或者顶部对齐。而 `QMUITextSizeSpan` 则可以多传一个参数，让你可以根据具体情况来决定偏移值。


## Helper Classes

### QMUIColorHelper</dt>
颜色处理工具类，按照功能类型来划分，总共包含以下几个特性：

1.  为一个颜色设置透明度。
2.  根据指定比例，在两个颜色值之间计算出一个颜色值。
3.  将颜色值转换为字符串。
      

### QMUIDeviceHelper</dt>
获取设备信息的工具类，按照功能类型来划分，总共包含以下几个特性：

1.  判断设备为手机/平板。
2.  判断设备是否为魅族手机。
3.  判断当前系统是否为 Flyme 系统。
4.  判断当前系统是否为 MIUI 系统。
5.  判断当前是否拥有悬浮窗权限。
      

### QMUIDisplayHelper</dt>
屏幕相关的工具类，按照功能类型来划分，总共包含以下几个特性：

1.  方便地获取一个 `DisplayMetrics` 实例。
2.  获取屏幕信息，包括屏幕密度、屏幕宽度和高度、状态栏高度、ActionBar 高度等。
3.  获取设备硬件信息，包括是否有可用摄像头、是否有硬件菜单、是否有网络、SD Card 是否可用、当前选择的国家语言等。
4.  判断当前是否处于全屏状态，控制进入/退出全屏状态。
5.  dp 与 px 数值的相互转化。


### QMUIDrawableHelper</dt>

1.  快速绘制一张指定大小、颜色、边框的图片，支持形状为圆角矩形和圆形。
2.  快速绘制一张带上分隔线或下分隔线的图片。
3.  快速绘制一张可带圆角的渐变图片。
4.  将当前图片的颜色换成另一个颜色。
5.  将两张图片叠加后生成一张新的图片。
6.  对某个 `View` 截图生成图片。
     

### QMUIPackageHelper</dt>
提供简便的方式获取 App 的版本信息，可以单独获取主版本号、次版本号以及修正版本号。

### QMUIResHelper</dt>
封装了更加便捷的方法，用于获取当前 `Theme` 下的 `Attr` 值，支持 `Float`、`Color`、`ColorStateList`、`Drawable` 和 `Dimen` 类型的 `Attr`。

### QMUISpanHelper</dt>
提供了方法使得 `QMUIMarginImageSpan` 能被更便捷地使用。

### QMUIStatusBarHelper</dt>
状态栏相关的工具类，按照功能类型来划分，总共包含以下几个特性：

1.  快速实现沉浸式状态栏（支持 4.4 以上版本的 `MIUI` 和 `Flyme`，以及 5.0 以上版本的其他 `Android`）。
2.  快速设置状态栏为黑色或白色字体图标（支持 4.4 以上版本 `MIUI` 和 `Flyme`，以及 6.0 以上版本的其他 `Android`）。
3.  提供多个常用的工具方法，如获取状态栏高度、判断当前是否全屏等等。
      

### QMUIViewHelper</dt>
View 工具类，按照功能类型来划分，总共包含以下几个特性：

1.  对 `ImageView` 进行处理，可以按比例缩放图片。
2.  对 `View` 做背景颜色变化动画，支持多个动画参数。
3.  对 `View` 做进退场动画，支持透明度变化和上下位移两种方式。
4.  提供多个常用的 `View` 相关工具方法，如对 `View` 设置单个方向的 `padding`、从 `ViewStub` 中获取一个 `View`、判断 `ListView` 是否已经滚动到底部等等。


































 
