package learn.web.thread.atomic;

import learn.util.test.DebugUtil;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 原子类操作测试
 * @date 2017-4-7下午2:13:24
 */
public class AtomicTest {

    //定义一个原子整数类
    private AtomicInteger atomicInt = new AtomicInteger(0);
    // 标识错误用法，使用不正确也会导致线程不安全
    private AtomicInteger errorUse = new AtomicInteger(0);
    private int num = 0;

    static {
//	    atomicInt.lazySet();
    }

    public static void main(String[] args) {
        AtomicTest test = new AtomicTest();
//        test.increment();

        test.atomicMethod();

    }

    /**
     * 测试自增，num++非线程安全，不能作为多线程下计数
     * @date 2023/1/14 13:02
     */
    void increment() {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                // 模拟业务
                DebugUtil.sleep(10);
                // num++非线程安全，100个线程每次加1理论上应该是100，而最终num的值很可能小于100
                // 因此多线程下计数可以考虑使用原子类AtomicInteger来保证线程安全
                num++;
                atomicInt.incrementAndGet();

                // 原子类在没有锁机制下执行多次也会出现线程不安全的问题，连续加两次，理论上应该是1000
                errorUse.addAndGet(9);
                errorUse.addAndGet(1);
            }).start();
        }
        // 等100个线程执行完成
        DebugUtil.sleepSeconds(5);
        System.out.println("num="+num + " atomicInt="+atomicInt.get() + " errorUse=" + errorUse.get());
    }

    /**
     * 测试原子类方法
     */
    void atomicMethod() {
        atomicInt.set(1);
        // 比较和更新：只有当内存值相等时才更新
        boolean isUpdate = atomicInt.compareAndSet(1, 200); // true
        System.out.println("isUpdate="+isUpdate+", atomicInt="+atomicInt.get());

        // 第二次执行不会更新，因为预期值不是1而是200
        isUpdate = atomicInt.compareAndSet(1, 2); // false
        System.out.println("isUpdate="+isUpdate+", atomicInt="+atomicInt.get());
    }

//
//        atomicInt.set(0);
//		AtomicTest test = new AtomicTest();
//		//多个线程访问共享的原子类对象count是线程安全的
//		new Thread(test).start();
//		new Thread(test).start();
//		new Thread(test).start();
//		new Thread(test).start();
//		new Thread(test).start();
//		new Thread(test).start();
//		new Thread(test).start();
//
//		// 模拟以上线程执行完后执行
//		Thread.sleep(5000);
//
//		// 多线程下num的值可能比atomicInteger的值少
//        System.out.println("多线程下int类型递增值为num="+num);
//        System.out.println("多线程下原子类递增值为AtomicInteger="+atomicInt.get());
//		System.out.println("多次调用原子方法在多线程环境下的值："+test.notSafe.get());
//	}
//
//	/**
//	 * 原子类方法测试
//	 * @date 2020/2/21 21:21
//	 **/
//	public static void atomicMethodTest() {
//	    // 设置初始值为0
//	    atomicInt.set(0);
//	    // 先自增再返回，类似++i
//	    int num = atomicInt.incrementAndGet();
//        System.out.println("num="+num +", atomicInt="+atomicInt.get());
//        // 先赋值再自增
//        num = atomicInt.getAndIncrement();
//        System.out.println("num="+num+", atomicInt="+atomicInt.get());
//
//        // 当值此时为2时更新值为100，返回true
//        boolean isUpdate = atomicInt.compareAndSet(2, 100);
//        System.out.println("isUpdate="+isUpdate+", atomicInt="+atomicInt.get());
//        // 第二次调用返回false，值不变
//        isUpdate = atomicInt.compareAndSet(2, 100);
//        System.out.println("isUpdate="+isUpdate+", atomicInt="+atomicInt.get());
//
//        System.out.println("---- 原子引用 ----");
//        AtomicReference<UserVO> atomicUser = new AtomicReference<>();
//        UserVO u1 = new UserVO(20);
//        UserVO u2 = new UserVO(100);
//        UserVO u3 = new UserVO(88);
//        // 初始原子应用变量
//        atomicUser.set(u1);
//        UserVO u = atomicUser.get();
//        System.out.println("atomicUser="+atomicUser.get().getAge());
//        // 线程T1模拟ABA的问题
//        new Thread(() -> {
//            atomicUser.set(u1);
//            atomicUser.set(u2);// 模拟中间修改过程
//            atomicUser.set(u1);// 最终结果还是不变
//            System.out.println("线程T1执行完成");
//        }, "T1").start();
//        new Thread(() -> {
//            // 休眠保证线程T1触发ABA的问题
//            DebugUtil.sleep(200);
//            if (atomicUser.compareAndSet(u1, u3)) {
//                System.out.println("线程T2修改成功");
//            }
//            System.out.println("线程T2执行完成");
//        }, "T2").start();
//
//        isUpdate = atomicUser.compareAndSet(u1, u2);
//        System.out.println("isUpdate="+isUpdate+", atomicUser="+atomicUser.get().getAge());
//        // 第二次调用返回false
//        isUpdate = atomicUser.compareAndSet(u1, u2);
//        System.out.println("isUpdate="+isUpdate+", atomicUser="+atomicUser.get().getAge());
//
//        // 通过AtomicStampedReference解决ABA的问题
//        AtomicStampedReference<UserVO> asr = new AtomicStampedReference<>(u1, 1);
//
//        // 以原子的方式设置某个类的字段值，该字段不能被private修饰且应该加上volatile修饰
//        System.out.println(u1.getId());
//        AtomicReferenceFieldUpdater<UserVO, String> arfu = AtomicReferenceFieldUpdater
//                .newUpdater(UserVO.class, String.class, "userId");
//        System.out.println(arfu.compareAndSet(u1, "a", "a111"));
//        System.out.println(arfu.get(u1));
//
//        // AtomicStampedReference解决ABA的问题，增加类似版本字段，每次修改对象都让版本加1
//        /*AtomicStampedReference<UserVO> asr = new AtomicStampedReference<>(u1, 1);
//        new Thread(() -> {
//            System.out.println("T1 start");
//            System.out.println(asr.compareAndSet(u1, u2, 1, 2));
//            System.out.println(Thread.currentThread() + asr.getReference().getUserId());
//            // 修改为最初的值
//            System.out.println(asr.compareAndSet(u2, u1, 2, 3));
//            System.out.println("T1 end");
//        }, "T1").start();
//
//        new Thread(() -> {
//            System.out.println("T2 start");
//            // 另一个线程尝试修改肯定不能成功
//            System.out.println(asr.compareAndSet(u1, u3, 1, 2));
//            System.out.println(asr.getReference().getUserId());
//            System.out.println("T2 end");
//        }, "T2").start();*/
//
//        // AtomicMarkableReference跟AtomicStampedReference类似，一个通过int类型来维护，一个通过布尔类型来维护
//        // AtomicMarkableReference也是为了解决ABA的问题，只不过AtomicStampedReference更加关注
//        // 对象被修改过几次，而AtomicMarkableReference只关注是否被修改过。
//        /*AtomicMarkableReference<UserVO> amr = new AtomicMarkableReference<>(u1, true);
//        new Thread(() -> {
//            System.out.println("T1 start");
//            System.out.println(amr.compareAndSet(u1, u2, false, true));
//            System.out.println(Thread.currentThread() + amr.getReference().getUserId());
//            // 修改为最初的值
//            System.out.println(amr.compareAndSet(u2, u1, true, true));
//            System.out.println("T1 end");
//        }, "T1").start();
//
//        new Thread(() -> {
//            System.out.println("T2 start");
//            // 另一个线程尝试修改肯定不能成功
//            System.out.println(amr.compareAndSet(u1, u3, false, true));
//            System.out.println(amr.getReference().getUserId());
//            System.out.println("T2 end");
//        }, "T2").start();*/
//
//        // 以原子的方式设置某个类的字段值，该字段不能被private修饰且应该加上volatile修饰
////        System.out.println(u1.getUserId());
//        AtomicReferenceFieldUpdater<UserVO, String> arfu1 = AtomicReferenceFieldUpdater.newUpdater(UserVO.class, String.class, "userId");
//        System.out.println(arfu1.compareAndSet(u1, "a", "a111"));
////        System.out.println(arfu.get(u1));
//
//        // int类型的原子字段更新
//        AtomicIntegerFieldUpdater<AtomicFieldUpdate> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(AtomicFieldUpdate.class, "count");
//        AtomicFieldUpdate afu = new AtomicFieldUpdate();
//        for (int i = 0; i < 120; i++) {
//            new Thread(() -> {
//                fieldUpdater.incrementAndGet(afu);
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(afu.getCount());
//    }
//
//    // 模拟被原子引用的对象
//    private static class User {
//	    private int age;
//
//	    User(int age) {
//	        this.age = age;
//        }
//
//        public int getAge() {
//            return age;
//        }
//    }
//}
//
//class AtomicFieldUpdate {
//
//    public volatile int count;
//
////    private final AtomicIntegerFieldUpdater<AtomicFieldUpdate> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(AtomicFieldUpdate.class, "count");
//
//    // 通过原子更新的方式实现线程安全的自增
//    void increment() {
//        count++;
////        fieldUpdater.incrementAndGet(this);
//    }
//
//    int getCount() {
//        return count;
//    }
//}
}