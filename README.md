# 淡江大學 202301

## 學期作業

1. 使用Maven來構建專案
2. 繳交程式碼與一份介紹作業內容(word)
3. 專題內容題目自訂(可與導師討論)

## 第一堂

### 課程大綱

- 使用Maven
- 使用IDE
- SpringBoot專案啟動
- 如何寫Controller


## 第二堂

### 課程大綱

- 套用log4j2
```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-log4j2</artifactId>
    </dependency>
```
- 套用Thymeleaf模板 (套用th tag)
```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
```
- 套用i18n
```
    src/main/java/org/tku/web/config/I18nConfiguration.java
```

## 第三堂

### 課程大綱
- i18n多國語系實作
```java
    @GetMapping("/index")
    public String index(@RequestParam(required = false) String locale,
                        HttpServletRequest request, HttpServletResponse response) {
        if(StringUtils.isNotBlank(locale)) {
            localeResolver.setLocale(request, response, new Locale(locale));
        }
        return "index";
    }
```
- 套用Thymeleaf模板 (套用layout tag)
```xml
    <dependency>
        <groupId>nz.net.ultraq.thymeleaf</groupId>
        <artifactId>thymeleaf-layout-dialect</artifactId>
    </dependency>
```

layout.html
```html
<html xmlns:th="http://www.thymeleaf.org" xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout" >
    <head>
        <meta charset="utf-8">
        <title>Title</title>
        <link rel="stylesheet" th:href="@{/css/bootstrap.min.css}">
        <script th:src="@{/js/bootstrap.min.js}" ></script>
    </head>
    <body>
        <th:block th:include="common/nav :: nav"></th:block>
        <div layout:fragment="content"></div>
        <th:block th:include="common/footer :: footer"></th:block>
    </body>
</html>
```
nav.html
```html
<html xmlns:th="http://www.thymeleaf.org">
    <body >
        <th:block th:fragment="nav">
          <nav class="navbar navbar-expand-lg bg-primary">
            <div class="container-fluid">
              <a class="navbar-brand text-white" href="#">TKU</a>
              <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                  <li class="nav-item">
                    <a class="nav-link active text-white" aria-current="page" th:href="@{/index}" th:text="#{common.index}">Home</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link text-white" th:href="@{/web/app1}" th:text="#{common.app1}"></a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link text-white" th:href="@{/web/app2}" th:text="#{common.app2}"></a>
                  </li>
                </ul>
              </div>
            </div>
          </nav>
        </th:block>
    </body>
</html>
```



## 第四堂

### 課程大綱
- 套用Spring Security，實作登入/登出功能
- pom.xml
```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
```

## 第五堂

### 課程大綱
- 使用資料庫postgres
- 套用spring-boot-starter-data-jpa
- 講解connection pool
```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
```


## 第六堂

### 課程大綱
- 使用資料庫postgres
- 撰寫User CRUD API的方式
- 講解CSRF攻擊
- Jackson的使用@JsonIgnore把特定欄位不回傳前端
```xml
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>2.15.2</version>
        </dependency>
```

```java
    http.csrf(httpSecurityCsrfConfigurer-> {
        httpSecurityCsrfConfigurer.ignoringRequestMatchers("/api/v1/**");
    });
```


## 第七堂

### 課程大綱
- 資料庫轉換postgresql變成h2 (因為有些人無法安裝)
- h2為輕量級資料庫，可以Embedded在JVM中，不須額外安裝
```xml
            <dependency>
                <groupId>com.h2database</groupId>
                <artifactId>h2</artifactId>
                <version>2.2.224</version>
            </dependency>
```
```properties
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.url=jdbc:h2:~/db
spring.datasource.username=sa
spring.datasource.password=ENC(urtjMImAhzPhQuiyPNr8V3m84EHdxkajxGhTW+q0SEUwA4mVI/7yu5u7GTnbCU3W)
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=false
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.datasource.type=com.zaxxer.hikari.HikariDataSource
spring.datasource.hikari.minimum-idle=2
spring.datasource.hikari.maximum-pool-size=30
spring.datasource.hikari.auto-commit=true
spring.datasource.hikari.idle-timeout=30000
spring.datasource.hikari.pool-name=tku-database
spring.datasource.hikari.max-lifetime=1800000
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.connection-test-query=SELECT 1
#關閉 Hibernate 參考資料庫 Metadata 加快啟動速度
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=false
spring.jpa.defer-datasource-initialization=true
spring.sql.init.mode=embedded
spring.sql.init.data-locations=classpath:/data.sql
spring.sql.init.schema-locations=classpath:/schema.sql
spring.sql.init.continue-on-error=true
```
- 使用jasypt來達到設定檔案資料加密 
```xml
            <dependency>
                <groupId>com.github.ulisesbocchio</groupId>
                <artifactId>jasypt-spring-boot-starter</artifactId>
                <version>3.0.5</version>
            </dependency>
```
```properties
#未加密↓
#spring.datasource.password=password 
#加密後↓
spring.datasource.password=ENC(urtjMImAhzPhQuiyPNr8V3m84EHdxkajxGhTW+q0SEUwA4mVI/7yu5u7GTnbCU3W)
```
```java
        StringEncryptor stringEncryptor = applicationContext.getBean(StringEncryptor.class);
        log.info(stringEncryptor.encrypt("password"));
```
- 加上H2Configuration.java -> 可以於網頁上下SQL查詢
```java
@Configuration
public class H2Configuration {
    @Bean
    org.h2.tools.Server h2Server() {
        Server server = new Server();
        try {
            server.runTool("-tcp");
            server.runTool("-tcpAllowOthers");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return server;

    }
}
```
- 介紹批次排程
```java
@SpringBootApplication
@Log4j2
@Component
@EnableJpaRepositories
@EnableEncryptableProperties
@EnableScheduling
public class WebApplication implements CommandLineRunner {
    ...
}
```
```java
@Component
@Log4j2
public class KeepAlive {

    /**
     * 每10秒執行一次
     * 秒 分 時 日 月 周 (年) 年是可選參數，不一定要寫，其他都是必須的
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void keepAlive1() {
        log.info("Cron => " + DateFormatUtils.format(new Date(), "yyyy/MM/dd'T'HH:mm:ss"));
    }


    /**
     * 執行完後休息一秒後再執行
     */
    @Scheduled(fixedDelay = 1000L)
    public void keepAlive2() {
        log.info("fixedDelay => " + DateFormatUtils.format(new Date(), "yyyy/MM/dd'T'HH:mm:ss"));
    }


    /**
     * 每兩秒執行一次
     */
    @Scheduled(fixedRate = 2000L )
    public void keepAlive3() {
        log.info("fixedRate => " + DateFormatUtils.format(new Date(), "yyyy/MM/dd'T'HH:mm:ss"));
    }
}
```



## 第八堂

### 課程大綱
- 撰寫Book CRUD UI的方式
- 修正上週無法使用h2的問題

> application-database.properties
```properties
spring.sql.init.mode=always
```

> Book.java
```java
@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "book_seq")
    private Long bookSeq;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "author")
    private String author;
    @Column(name = "price")
    private Long price;

    @Transient
    private String action;
}
```

> BookRepositoryCustomImpl.java
```java
    @Override
    public List<Book> findBooksBySelective(Book book) {
        return bookRepository.findAll((root, query, cb) -> {
            query.distinct(true);
            query.orderBy(cb.asc(root.get("bookSeq")));

            final List<Predicate> predicates = new ArrayList<>();
            if (book.getBookSeq() != null) {
                predicates.add(cb.equal(root.get("bookSeq"), book.getBookSeq()));
            }

            if (StringUtils.isNotBlank(book.getBookName())) {
                predicates.add(cb.like(root.get("bookName"), "%" + book.getBookName() + "%"));
            }

            if (StringUtils.isNotBlank(book.getAuthor())) {
                predicates.add(cb.like(root.get("author"), "%" + book.getAuthor() + "%"));
            }

            if (book.getPrice() != null) {
                predicates.add(cb.equal(root.get("price"), book.getPrice()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
```

> BookController.java
```java    
@GetMapping(value = "/web/book")
    public String index(@RequestParam(value = "bookSeq", required = false) Long bookSeq,
                        @RequestParam(value = "bookName", required = false) String bookName,
                        @RequestParam(value = "author", required = false) String author,
                        @RequestParam(value = "price", required = false) Long price,
                        Model model) {

        Book book = new Book();
        book.setBookSeq(bookSeq);
        book.setBookName(bookName);
        book.setAuthor(author);
        book.setPrice(price);
        List<Book> books = bookRepository.findBooksBySelective(book);
        model.addAttribute("books", books);
        model.addAttribute("book", book);
        
        return "book/index";
    }
```



## 第九堂

### 課程大綱
#### 訊息傳遞
- 使用TCP Socket傳遞訊息
- 使用activeMQ Artemis傳遞訊息
- 使用Http傳遞訊息

### 初次使用Linebot

## 第十堂

### 課程大綱
- Linebot實作
- 使用Linebot SpringBoot SDK
