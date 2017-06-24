# SimpleFragmentManager 

Simplified handling of fragments in android application by using wrapper class around `FragmentManager` and simple `tag` stack.

Detailed explanation is in this __[blog post](http://bajicdusko.com/2017/simplified-fragment-management/)__


[![SimpleFragmentManager](https://img.shields.io/badge/Build-1.0.2-green.svg)](https://bintray.com/bajicdusko/maven/fragment-manager/1.0.2/link1)

### Installation

Gradle
```
implementation 'com.bajicdusko:fragment-manager:1.0.3'
```
or
```
compile 'com.bajicdusko:kotlin-fragment-manager:1.0.3'
```

### Usage example

To simplify the setup, few super classes are created: `SFMActivity`, `SFMFragment` and `FragmentChannel`. 
By extending these three classes you should be ready in no time.

Create new communication interface by extending `FragmentChannel`
```kotlin
interface ExampleFragmentChannel : FragmentChannel {
    fun openSecondFragment()
}
```

Each new fragment handled by `SimpleFragmentManager` should extend `SFMFragment<>`
```kotlin
class FirstFragment : SFMFragment<ExampleFragmentChannel>() { 

    ...
    
    fun onButtonClick(){
        fragmentChannel?.openSecondFragment()
    }
}
```

By creating new activity, just extend `SFMActivity`
```kotlin
class HomeActivity : SFMActivity(), ExampleFragmentChannel {

    override fun getFrameLayoutContainerId(): Int = R.id.activity_home_fragment_container

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        simpleFragmentManager.enableLogs(true)
        simpleFragmentManager.addFragment(FirstFragment.newInstance())
    }
    
    override fun openSecondFragment(){
        simpleFragmentManager.replaceFragment(SecondFragment.newInstance())
    }
}
```

### This is what happens in the background

![SequenceDiagram](http://bajicdusko.com/images/posts/2017/2017-06-08-fragmentdiagram.svg)
