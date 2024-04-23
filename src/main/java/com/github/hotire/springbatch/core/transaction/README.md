# Transaction


## MySQL(wait_timeout) / HikariCP(maxLifetime) / Chunk

- https://jojoldu.tistory.com/526

### wait_timeout (MySQL)

- MySQL과 클라이언트가 연결을 맺은 후, 다음 쿼리까지 기다리는 최대 시간을 의미합니다.
- MySQL에 연결된 클라이언트 (여기서는 WAS등)가 지정된 wait_timeout 시간 동안 쿼리 요청이 없는 경우 MySQL은 해당 Connection(connection) 을 강제로 종료해버립니다.
- 기본값은 28800이며, 단위는 초(s) 라서 실제 기본 값은 8시간입니다.


### maxLifetime (HikariCP)

- Connection Pool에서 살아있을 수 있는 Connection의 최대 수명시간입니다.
- Connection Pool레벨에서 maxLifetime이 지나도록 idle 상태인 connection 객체를 pool에서 제거합니다.
- 사용중인 Connection은 maxLifetime에 상관없이 제거되지 않습니다
  - 사용중이지 않을 때만 제거됩니다.
- Connection Pool 전체가 일괄로 같은 값 (maxLifetime)이 적용되지 않고, 2.5%의 버퍼를 둡니다.
  - 풀에서 동시에 대량의 Connection들이 제거되는 것을 방지하기 위함입니다.
- 기본값은 1800000이며, 단위는 ms라서 실제 기본 값은 30분입니다.
  - 0으로 지정하시면 무한대가 됩니다. (주의)


~~~
logging:
  level:
    com.zaxxer.hikari.HikariConfig: DEBUG
~~~

- HouseKeeper : HikariCP에서 사용하는 Connection Pool 관리 Thread입니다.