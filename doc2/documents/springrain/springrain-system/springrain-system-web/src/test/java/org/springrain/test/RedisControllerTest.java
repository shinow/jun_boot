package org.springrain.test;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springrain.SpringrainApplication;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringrainApplication.class)
public class RedisControllerTest {

    /**

     @Resource private RedisOperation redisOperation;

     @Resource private IStockService stockService;

     private int Num = 1000;
     @Test public void atomic() {

     Stream.iterate(0,x->x+1).limit(5000).forEach(x -> {
     Num++;


     Thread t = new Thread() {
     public void run() {


     String  ukey =   "goods_id_stock_key_unit" + 1;
     boolean lock = redisOperation.lock(ukey, 1000);

     if(lock){
     System.out.println(Num);
     Stock stock = null;
     try {
     stock = stockService.findStockById("1");
     } catch (Exception e) {
     e.printStackTrace();
     }

     stock.setNum(stock.getNum() -1 );

     try {
     stockService.update(stock);
     } catch (Exception e) {
     e.printStackTrace();
     }


     redisOperation.unlock(ukey);

     }else{

     //  System.out.println("end =============="+Num);
     }

     }
     };


     t.start();

     });



     }



     @Test public void atomicStock() throws Exception {

     String  ukey =   "goods_id_stock_key_atomic" + 1;
     Stock stock = null;
     stock = stockService.findStockById("1");
     RAtomicLong atomicLong = redisOperation.getAtomicLong(ukey, stock.getNum().longValue());


     Stock finalStock = stock;

     Stream.iterate(0, x->x+1).limit(50000).forEach(x -> {
     Num++;



     Thread t = new Thread() {
     public void run() {


     long l = atomicLong.decrementAndGet();
     // atomicLong.addAndGet(-10)

     finalStock.setNum(  (int) l  );

     try {
     stockService.update(finalStock);
     } catch (Exception e) {
     e.printStackTrace();
     }

     System.out.println("end =============="+Num);


     }
     };


     t.start();

     });



     }

     */

}
