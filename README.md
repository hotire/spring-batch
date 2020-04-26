# Spring-Batch

![conversion](/doc/overflow.png)

- 대용량 데이터
- 자동화 (버그를 제외하고는 개발자 개입 없이 실행)
- 견고성 (잘못된 데이터를 충돌/중단 없이 처리)
- 신뢰성 (로깅, 알람 등을 통한 추적)
- 성능 (다른 어플리케이션을 방해하지 않고, 지정된 시간 안에 처리)

### vs Web

- Web : 실시간 처리 / 상대적인 속도 / QA 용이

- Batch : 후속 처리 / 절대적인 속도 / QA 복잡성 


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