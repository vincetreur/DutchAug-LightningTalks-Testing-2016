# Some Android testing tricks

## Lightning talk
A 15 minute talk about testing with Android Studio 2.0 for the DutchAUG.
Since there was not much to tell about testing in Studio, I focused mainly on some tetsing tricks.

## New in Android Studio 2.0
Refactoring just become easier since you don't have to switch between unit tests and instrumented tests.

## Tricks

### Non-stop JUnit tests
```./gradlew testDebugUnitTest -t```

Then just keep improving your code and tests, while Gradle keeps running your tests in the background.

### JUnit's small/medium/large tests
1. Annotate your test methods as usual.
2. According to the docs you should call
```adb shell am instrument -w -e size small com.appsingularity.testing.test/android.support.test.runner.AndroidJUnitRunner```
3. *Frown*
4. This will fail beacuse you forgot to install both the APK and test-APK. At least I did.
5. Instead call ```./gradlew connectedDebugAndroidTest -Ptags="@android.test.suitebuilder.annotation.SmallTest"```

Ofcourse this needs a custom gradle task.
```
android {
   defaultConfig {
	   // Check for tag existence
	   def TAG_PARAM = "tags"
	   if (project.hasProperty(TAG_PARAM)) {
		   logger.info "Found tag(s) ${project.getProperties().get(TAG_PARAM)} "
		   // Split tags up
		   def separateTags = project.getProperties().get(TAG_PARAM).split(/,/)
		   separateTags.each {
			   // Check for ~ prefix
			   if (it.startsWith("~@")) {
				   logger.info "Excluding tag ${it.substring(2)} "
				   testInstrumentationRunnerArgument "notAnnotation", "${it.substring(2)}"
			   } else if (it.startsWith("@")) {
				   logger.info "Including tag ${it.substring(1)} "
				   testInstrumentationRunnerArgument "annotation", "${it.substring(1)}"
			   } else {
				   logger.warn "What's this tag ${it}?"
			   }
		   }
	   }
   }
}
```

Now you can also define your own annotations, like...
```
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ErrorTest {
}
```
or
```
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FractalTest {
}
```

Now run tests with a specific annotation, call
``./gradlew connectedDebugAndroidTest -Ptags="@com.appsingularity.testing.ErrorTest"``

Or tests *without* a specific annotation.
```./gradlew connectedDebugAndroidTest -Ptags="~@com.appsingularity.testing.ErrorTest"```


#### Things to know, to test like a pro

- uIf you mess up your annotations, all your tests will run.
- ```size```, ```annotation``` and ```notAnnotation``` are evaluated as ```AND```, so your test method will need to match *all* of them.
- ```testInstrumentationRunnerArgument``` is a map in Gradle, so ```annotation``` and ```notAnnotation``` can each only be used once?


