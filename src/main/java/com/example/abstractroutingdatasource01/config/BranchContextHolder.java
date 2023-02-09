package com.example.abstractroutingdatasource01.config;

public class BranchContextHolder {
    private static ThreadLocal<BranchEnum> threadLocal = new ThreadLocal<>();
    // ThreadLocal 클래스는 thread-local 변수들을 제공한다.
    // 이 변수들은 get 또는 set 메소드를 통해 접근하는 각 스레드가 독립적으로 변수의 초기화 된 사본을 가지고 있다는 점에서 다르다.
    // ThreadLocal 인스턴스들은 보통 스레드와 상태를 연결하려고 하는 클래스들의 private static 필드들이다. (예를들어, 유저 ID 또는 트랜잭션 ID)

    // AbstractRoutingDataSource 에서 사용될 DataSource의 lookup key를 저장하는 용도로 사용된다.
    // 현재 컨텍스트에서 사용될 DataSource의 key(BranchEnum 타입)을 ThreadLocal에 저장하고
    // AbstractRoutingDataSource 에서 ThreadLocal에 저장된 값을 참조하여 타겟 DataSource를 결정한다
    public static void setBranchContext(BranchEnum branchEnum) {
        threadLocal.set(branchEnum);
    }

    public static BranchEnum getBranchContext(){
        return threadLocal.get();
    }

    public static void clearBranchContext(){
        threadLocal.remove();
    }
}
