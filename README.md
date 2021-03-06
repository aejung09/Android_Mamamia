# Android_Momamia


## 1.Build gradle에 추가를 한다. 

```java
    dependencies {........

     //리사이클러뷰
     
        implementation 'com.android.support:recyclerview-v7:30.0.0'


    //Google Play Services 라이브러리 추가
        implementation 'com.google.android.gms:play-services-maps:17.0.0'
        implementation 'com.google.android.gms:play-services-location:17.1.0'

    //Google places 라이브러리 추가
        implementation 'noman.placesapi:placesAPI:1.1.3'

    //Google 거리재기 라이브러리 추가
        implementation 'com.google.maps.android:android-maps-utils:0.5'



    //사진 업로드 관련 라이브러리 추가
        implementation 'com.github.bumptech.glide:glide:4.11.0'
        implementation 'com.squareup.picasso:picasso:2.71828'
        annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'
    }
 ```
------------



## 2.Andorid에 권한을 추가한다.


### 1.Manifest에 아래코드를 추가한다.
``` xml
        <uses-permission android:name="android.permission.INTERNET" />
        <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
        <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
        <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

        <application.....
            android:networkSecurityConfig="@xml/network_security_config">

        <uses-library android:name="org.apache.http.legacy"
            android:required="false"/>
  ```          
### 2.Tomcat 서버와 연동하기 위해 src폴더안에 /xml 폴더를 만든다.

이후 xml 폴더안에 network_security_config.xml 이라는 XML Resource File 을 생성한다.

Network_security_config.xml 안에 이 코드를 삽입한다.

``` xml
    <?xml version="1.0" encoding="utf-8"?>
    <network-security-config>
    <base-config cleartextTrafficPermitted="true">
            <trust-anchors>
                <certificates src="system"/>
            </trust-anchors>
        </base-config>
    </network-security-config>
```
-------
## 3.Tomcat에 연동할 jsp 파일을 폴더에 넣는다.

방법은 tomcat 라이브러리 폴더의 /webapps/ROOT/안에 넣으면 된다.


Link: [JSP파일 Link](https://github.com/aejung09/Android_Momamia/tree/main/JspFile)


---
## 4.Tomcat에 사진을 넣기 위해 pictures 폴더를 만든다.

방법은 tomcat 라이브러리 폴더의 /webapps/ROOT/안에 pictures폴더를 생성하면 된다.


----
## 5.MYSQL 라이브러리를 넣는다

방법은 connector를 받아 tomcat 라이브러리 폴더의 /lib 에 넣어주면 된다.

참고) windowOS는 /webapps/ROOT/WEB-INF에 cos.jar를 넣어주면 된다. MYSQL Connector는 이전과 동일하게 /lib에 넣어주면 된다.

Link: [MYSQL Connector](https://github.com/aejung09/Android_Momamia/tree/main/MySQL%20Connector)

---

## 6.test 폴더 안에 jsp MYSQL 데이터 베이스의 주소를 수정하도록한다.
이를테면
    
 ``` java
    String stSearch =  request.getParameter("addrName");

    String url_mysql = "jdbc:mysql://이부분에 데이터베이스주소를 수정하세요/MYSQL스키마이름?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";

    String id_mysql = "아이디";

    String pw_mysql = "암호";
```
한글로 적혀있는 부분은 각자 환경에 맞게 채우면 된다.


----
## 7.SQL Table 형식은 다음과 같다.
addrNo|addrName |	addrTel	|addrAddr|	addrDetail|	addrLike	|addrTag	|addrImagePath|
---|---|---|---|---|---|---|---|
int(11),AI,PK|	varchar(45)	|varchar(45)	|varchar(150)|	varchar(45)	|varchar(45),Default'0'|	varchar(45)|	varchar(100)

----
## 8.실행영상

[<img width="230" alt="스크린샷 2021-02-18 오후 5 26 48" src="https://user-images.githubusercontent.com/70096347/108327287-93389680-720e-11eb-8a76-3ae374c8cba1.png">](https://www.youtube.com/watch?v=-QOxN_vmg-4)

이미지 클릭시 영상띄워짐.
