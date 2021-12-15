# Step

## Chunk

Spring Batch에서의 Chunk란 데이터 덩어리로 작업 할 때 각 커밋 사이에 처리되는 row 수를 얘기합니다.
즉, Chunk 지향 처리란 한 번에 하나씩 데이터를 읽어 Chunk라는 덩어리를 만든 뒤, Chunk 단위로 트랜잭션을 다루는 것을 의미합니다.

여기서 트랜잭션이라는게 중요한데요.
Chunk 단위로 트랜잭션을 수행하기 때문에 실패할 경우엔 해당 Chunk 만큼만 롤백이 되고, 이전에 커밋된 트랜잭션 범위까지는 반영이 된다는 것입니다.


### ChunkOrientedTasklet
Chunk 지향 처리의 전체 로직을 다루는 것은 ChunkOrientedTasklet 클래스입니다.
클래스 이름만 봐도 어떤 일을 하는지 단번에 알 수 있을것 같습니다.

### SimpleChunkProvider



### Chunk 지향 
- https://jojoldu.tistory.com/331