# SpringStudy
24.08 Spring 공부 시작

![스프링](https://github.com/user-attachments/assets/9f83a1f8-4054-4794-b3b3-279578ecf303)


8/12

스프링에서 빠지지 않는 개념이 "의존성 주입" 이다.
의존성 = 하나의 객체가 다른 객체 없이 제대로 된 역할을 할 수 없다는 의미하며, 하나의 객체가 다른 객체의 상태에 다라 영향을 받는 것을 의미.
주입 = 밀어넣는 것

ApplicationContext 라는 필요한 객체를 생성, 주입하는 역할을 해주는 구조/ 스프링에서는 객체와 객체를 분리해서 생성하고 이러한 객체들을 엮는 작업(wiring)을 한다.
ApplicationContext가 관리하는 객체들을 빈(bean) 이라고 하며, 빈과 빈 사이의 의존관계 처리 방식으로 xml설정, 어노테이션 설정, java 설정을 이용할수가 있다.

스프링에서 반드시 처리가 필요한 부분을 횡단 관심사 라고 하는데 이를 분리해서 제작하는 것이 가능한데 AOP가 이러한 횡단 관심사를 모듈로 분리하는 프로그래밍의 패러다임이다.

데이터베이스를 이용할때 반드시 신경 써야하는 부분이 트랜젝션 처리//
스프링에서는 트랜젝션의 관리를 어노테이션, XML로 설정할 수 있기 때문에 개발자가 매번 상황에 맞는 코드를 작성할 필요가 없게 설계 되어 있다.

*lombok은 setter 생성 기능과 생성자, toString 등 자동으로 생성하도록 @Data 어노테이션 이용한다.

@Component는 스프링에게 해당 클래스가 스프링에서 관리해야 하는 대상임을 표시하는 어노테이션
@Setter는 자동으로 컴파일 시 생성

*root-context은 스프링 프레임워크에서 관리해야하는 객체를 설정하는 파일 -> NameSpace에서 context라는 항목을 체크 해줘야 한다.
-> component-scan 코드를 추가해야 한다.

스프링이 사용하는 메모리영역을 만들게 되는데 Context라고 하며 스프링에서는 ApplicationContext라는 이름의 객체가 만들어 진다.
객체를 생성하고 관리해야하는 객체들의 설정이 필요한데 이것을 root-context에서 한다.

*Test Code
테스트 코드가 스프링 역할 할것이라는 표시 -> @Runwith
@ContextConfiguration = 지정된 클래스, 문자열 이용해서 필요한 객체들을 스프링 내에서 객체로 등록하게 됨(스프링에서는 빈을 등록했다고 표현)
  사용하는 문자열은 classpath, file 이용할 수 있으며 자동으로 생성된 root-context.xml의 경로를 지정할 수가 있다.

@log4j2 = lombok을 이용해서 로그를 기록하는 logger를 변수로 생성.
@Autowired = 해당 인스턴스 변수가 스프링으로부터 자동으로 주입해 달라는 표시
@Test = Junit에서는 테스트 대상을 표시하는 어노테이션
AssertNotNull = 변수가 null이 아니어야만 테스트가 성공한다는 것을 의미.

새 객체 생성할 때 new를 사용해서 생성해야하는데 생성한적이 없었는데도 생성이 된점 = 스프링이 어노테이션을 이용해서 객체를 생성하고 관리하는 컨테이너, 팩토리 기능을 가지고 있다.
@Data 어노테이션으로 lombok 이용해서 여러 메서드가 만들어진점 = lombok이 자동으로 getter/setter 생성하고 onMethod속성을 이용해서 작성된 setter에 @Autowired 어노테이션 추가한다.
또한 @Data는 lombok에서 가장 사용되는 어노테이션이므로 @toString, @EqulasAndHashCode, @setter/getter, @RequiredArgsConstructor 를 모두 결합한 형태로 모든 메서드들을 생성할 수 있다는 장점이 있다.
세부적인 설정이 필요 없다면 @Data를 주로 사용 한다.

@Componet는 해당 클래스가 스프링에서 객체로 만들어서 관리하는 대상임을 명시하는 어노테이션 
  -> 존재하는 클래스들을 객체로 생성해서 빈으로 관리하게 한다.

@Autowired는 스프링 내부에서 자신이 특정한 객체에 의존적이므로 자신에게 해당 타입의 빈을 주입해주라는 표시
  -> 스프링은 이 어노테이션을 보고 내부에 관리되는 객체들 중 적당한 것이 있는지 확인하고 자동으로 주입해 준다.

@ContextConfiguration 은 테스트 관련 가장 중요한데, 스프링이 실행되면서 어떤 설정 정보를 읽어 들어야하는지 명시한다.
  -> 속성으로 locations을 이용하여 문자열의 배열로 xml 설정을 명시
      classes속성으로 Configuration이 적용된 클래스를 지정해 줄수 있다.
@Runwith 는 테스트 시 필요한 클래스를 지정
@Test는 junit에서 해당 메서드가 junit상에서 단위 테스트의 대상인지 알려 준다.

@AllArgsConstructor는 인스턴스 변수로 선언된 모든 것을 파라미터로 받는 생성자를 작성하게 된다.
@RequiredArgsConstructor는 @NotNull/ final이 붙은 인스턴스 변수에 대한 생성자를 만들어 낸다.



