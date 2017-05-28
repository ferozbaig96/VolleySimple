# VolleySimple
Android Library to make API calls simpler using Google Volley

### Specs
[![](https://jitpack.io/v/ferozbaig96/VolleySimple.svg)](https://jitpack.io/#ferozbaig96/VolleySimple)

### Show some :heart:
[![GitHub stars](https://img.shields.io/github/stars/ferozbaig96/VolleySimple.svg?style=social&label=Star)](https://github.com/ferozbaig96/VolleySimple) [![GitHub forks](https://img.shields.io/github/forks/ferozbaig96/VolleySimple.svg?style=social&label=Fork)](https://github.com/ferozbaig96/VolleySimple/fork) [![GitHub watchers](https://img.shields.io/github/watchers/ferozbaig96/VolleySimple.svg?style=social&label=Watch)](https://github.com/ferozbaig96/VolleySimple) [![GitHub followers](https://img.shields.io/github/followers/ferozbaig96.svg?style=social&label=Follow)](https://github.com/nisrulz/UploadToJitpack)  

## Setup and usage

To include this library to your project add dependency in **build.gradle** file:

```groovy

    repositories {
        jcenter()
        **maven { url 'https://www.jitpack.io' }**

    }
    
    dependencies {
        **compile 'com.github.ferozbaig96:VolleySimple:1.0'**
    }
```

Then in your Activity :

```JAVA
    RequestManager.getInstance(this)
                .placeJsonObjectRequest(
                "myTag",  // tag (optional)
                url,      // url of the request
                Request.Method.GET,   // Request.Method.GET or Request.Method.POST
                params,    // parameters (optional)
                headers,   // http headers (optional)
                this);     // callback for handling response
```

Then make your Activity implement **ServerCallback**

```JAVA
    public class MainActivity extends AppCompatActivity implements **ServerCallback** {
```

Handle the response in your Activity 

```JAVA
    @Override
    public void onAPIResponse(String apiTag, Object response) {
       //handle response
    }

    @Override
    public void onErrorResponse(String apiTag, VolleyError error) {
       // handle error 
    }
```

## Additional Options

Apart from JsonObjectRequest, you can make the following requests

```JAVA
    // StringRequest
    RequestManager.getInstance(this)
                .placeStringRequest(...);
    
    // JsonArrayRequest
    RequestManager.getInstance(this)
                .placeJsonArrayRequest(...);
                
```

## Customizations

### Set timeout for requests

```JAVA
    RequestManager.getInstance(this)
                .setInitialTimeoutMs(5000) // Default value = 3000
                .placeJsonObjectRequest(...);         
```

### Set maximum no. of retries for requests

```JAVA
    RequestManager.getInstance(this)
                .setMaxNoOfTries(4) // Default value = 2
                .placeJsonObjectRequest(...);         
```

## Tips & Tricks

GSON for handling responses

```JAVA

  // Create your own GSON Pojo Class. Here, SamplePojo represents a GSON Pojo
  
   @Override
    public void onAPIResponse(String apiTag, Object response) {
        if ("SIGN_UP_REQUEST".equals(apiTag)) {
            SamplePojo samplePojo = new Gson().fromJson(response.toString(), SamplePojo.class);
  
            displayname_textview.setText(samplePojo.getName);
            age_textview.setText(samplePojo.getAge());
            ...
        }
    }
```

<br />

## Changelog

| Version | Changes                         |
| --- | --- |
| v.1.0.0 | First public release            |

<br />

License
=======

    Copyright 2017 Feroz Baig

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
