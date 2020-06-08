[![](https://jitpack.io/v/vijaypatidar31/transmitter.svg)](https://jitpack.io/#vijaypatidar31/transmitter)
# Transmitter
Transmitter is a simple java library used for sending and receiving serialised object through java socket I/O stream,I build it for my project it can also be used for building multiplayer game like Tic-tac-toe and other easily.
# Working
This java library work in two phases 
* Server - Communicate with multiple clients
* Client - Communicate with the server only

## Server features 
* Broadcast serialised object
* Unicast serialised object
* Manage clients connection

## Client features 
* Unicast serialised object to server
* Receive serialised object

# Download
#### Step 1 : Add the JitPack repository to your build file
Gradle:
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Maven:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

#### Step 2 : Add the dependency

Gradle:
```gradle
dependencies {
  implementation 'com.github.vijaypatidar31:transmitter:1.1'
}
```

Maven:
```xml
<dependency>
    <groupId>com.github.vijaypatidar31</groupId>
    <artifactId>transmitter</artifactId>
    <version>1.1</version>
</dependency>
```
    
# License
MIT License

Copyright (c) 2020 Vijay Patidar

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
