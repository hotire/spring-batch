# Spring-Batch

![conversion](/doc/overflow.png)

- 대용량 데이터
- 자동화 (버그를 제외하고는 개발자 개입 없이 실행)
- 견고성 (잘못된 데이터를 충돌/중단 없이 처리)
- 신뢰성 (로깅, 알람 등을 통한 추적)
- 성능 (다른 어플리케이션을 방해하지 않고, 지정된 시간 안에 처리)


사용자와 상호 작용 없이 이어재는 프로그램(작업) 들의 실행이다. 

### vs Web

- Web : 실시간 처리 / 상대적인 속도 / QA 용이

- Batch : 후속 처리 / 절대적인 속도 / QA 복잡성 

### vs Quartz

쿼츠는 스케줄링 프레임워크

배치 방법 중 스케줄링에 해당되는 걸로, 쿼츠는 Batch의 보안제 역할이지 대체제가 아니다.  

### 관리 도구들 

- Cron 

- Spring MVC + API Call

- Spring Batch Admin (Deprecated)

- Quartz + Admin 

- CI Tools (Jenkins)



### Job

배치 작업 단위로 여러 Step 으로 구성된다.

- Next : 순차적으로 Step 의 순서를 제어한다. 

- Flow : 조건별 흐름 제어 (Fail, Success 제어)

- Decide : Step Flow 분기만 담당한다.

### Status

- Batch Status : Job 또는 Step 의 실행 결과 Enum

- ExitStatus : Step 의 실행 후 상태 (Enum 이 아님)

### Step

Tasklet, Reader & Processor & Writer 묶음이다.

### Scope

Spring Batch 해당 Scope 동안 Bean 으로 생성한다.

- StepScope : Step 실행 시점 생성

- JobScope : Job 실행 시점 생성


### MetaTable

- BATCH_JOB_INSTANCE
  - JOB_INSTANCE_ID : PK
  - JOB_NAME : Batch Job Name 
  
  : Job Parameter(외부에서 받을 수 있는 파라미터)에 의해 생성된다. 
  
- BATCH_JOB_EXECUTION
  - JOB_INSTANCE, JOB_EXECUTION_ID : 부모 자식 관계로, JOB_INSTANCE 성공/ 실패 내역 관리
  
- BATCH_JOB_EXECUTION_PARAM
  - KEY_NAME : Job Parameter Key
  - VAL : Job Parameter Value
  
    
### ItemWriter


### Getting Started  

- https://spring.io/guides/gs/batch-processing/


## Test

### 주의할점 

1. Environment 가 변경될 경우 SpringBootContext 가 새로 시작된다. 
- 테스트 코드에서 @MockBean / @SpyBean 사용할 때
- 테스트 코드에서 @TestPropertySource로 환경 변수가 변경할 때 
- @ConditionalOnProperty로 테스트마다 Config가 다를 떄 


## Batch-Rest

https://github.com/chrisgleissner/spring-batch-rest


## References

- https://godekdls.github.io/Spring%20Batch/configuringandrunningajob/
- https://kwonnam.pe.kr/wiki/springframework/batch