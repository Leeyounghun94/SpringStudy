# SpringStudy
24.08 Spring 공부 시작

08.19 

Model 객체는 jsp에 컨트롤러에서 생성된 데이터를 담아서 전달하는 역할을 하는 존재/ jsp와 같은 뷰로 전달해야 하는 데이터를 담아서 보낼 수 있다. 
  model 타입이 지정되는 경우 스프링은 특별하게 model 타입의 객체를 만들어서 메서드를 주입하게 된다.

model = rquest.setAttribute 유사한 역할

@modelAttribute -> 파라미터로 전달된 데이터는 존재하지 않지만, 화면에서 필요한 데이터를 전달 하기 위해서 사용한다.
  -> 강제로 전달 받은 파라미터를 Model에 담아서 전달하도록 할 때 필요한 어노테이션// 타입에 관계없이 무조건 Model에 담아서 전달됨.
      @ModelAttribute("page") -> url 통해서 넘어온 값을 model에 담는다.

RedirectAttribute 타입은 특별하게도 일회성으로 데이터를 전달하는 용도로 사용 된다.

* Controller 의 리턴 타입
  1.String : jsp 이용할 경우 jsp파일의 경로와 파일 이름을 나타내기 위해서 사용
    String 타입은 상황에 따른 다른 화면을 보여줄 필요가 있을 경우 유용하게 사용한다.
    현재 프로젝트의 경우 jsp 파일의 이름을 의미한다.
  
  2.void : 호출하는 url과 동일한 이름의 jsp 의미
  
  3.VO, DTO : 주로 JSON 타입의 데이터를 만들어서 반환하는 용도 사용
    브라우저에서 json 타입으로 객체를 변환해서 전달한다.
    스프링MVC에서는 리턴 타입에 맞게 데이터를 변환해 주기 때문에 JSON처리의 별도의 설정은 필요하지 않다!
  
  4.ResponseEnitiy : response 할 때 http 헤더 정보와 내용을 가공하는 용도 사용
    WEB을 다루다 보면 HttpServletRequet, HttpServletResponse를 직접 핸들링 안해도 작성이 되었기 때문에 ResponseEnity 통해서 원하는 헤더 정보, 데이터 전달 할수       있다.
    ResponseEnitiy는 HttpHeader 객체를 같이 전달할수 있으며 HTTP 헤더 메세지를 가공하는 것이 가능하다.
  
  5.HttpHeaders : 응답에 내용 없이 http헤더 메세지만 전달하는 용도로 사용.

  
* 파일 업로드 처리
  servlet-context.xml은 스프링 mvc의 특정한 객체를 설정해서 파일을 처리한다.
  파일 업로드의 경우 반드시 id 속성의 값을 multipartResolver로 정확하게 지정해야 한다.

    1. maxUploadSize = 한번의 request로 전달될수 있는 최대의 크기를 의미
    2. maxUploadSizePerfile = 하나의 파일 최대 크기
    3. maxInMemorySize = 메모리상에서 유지하는 파일의 최대 크기(단, 이 크기 이상 데이터는 uploadTempDir에 임시 파일 형태로 보관됨)

  스프링 MVC는 전달되는 파라미터가 동일한 이름으로 여러 개 존재한다면 배열로 처리가 가능하므로 파라미터를 MultiPartFile의 배열 타입로 한다.



  * Advice
    @ControlloerAdvice는 AOP를 이용하는 방식이며 AOP는 공통적인 관심사 분리 라는 개념이다. 중복적이고 많은 양의 코드를 작성해야 하나, AOP방식을 이용하면
    공통적인 예외사항은 별도로 @ControlloerAdvice 이용해서 분리하는 방식이다.

    @ControllerAdvice = 해당 객체가 스프링의 컨트롤러에서 발생하는 예외를 처리하는 존재임을 명시하는 용도로 사용
    @ExceptionHandler = 해당 메서드가 () 들어가는 예외 타입을 처리한다는 의미.
      속성으로 Exception 클래스 타입을 지정할 수 있으며 모든 예외 처리가 except()만을 이용해서 처리 할 수가 있다.
      만일 특정한 예외를 다루고 싶다면 Exception.class 대신 구체적인 예외 클래스를 지정해야 한다.
      jsp 화면에서도 구체적인 메세지를 보고 싶다면 model를 이용해서 전달하는 것이 좋다.

  * Error 페이지
    흔히 보이는 400과 500 에러코드에서
    500 = @ExceptionHandler 이용해서 처리할 수 있다.
    400 = url 잘못 호출 할때 보이는 에러 코드이지만 다르게 처리하는 것이 좋다. -> custom404 
     
