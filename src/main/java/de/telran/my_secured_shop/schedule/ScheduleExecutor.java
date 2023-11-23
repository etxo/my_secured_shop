package de.telran.my_secured_shop.schedule;

import de.telran.my_secured_shop.domain.entity.Task;
import de.telran.my_secured_shop.services.jpa.JpaProductService;
import de.telran.my_secured_shop.services.jpa.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@EnableScheduling
@EnableAsync
public class ScheduleExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleExecutor.class);
    private TaskService service;
    private JpaProductService productService;

    public ScheduleExecutor(TaskService service) {
        this.service = service;
    }

    /*
        @Scheduled(fixedDelay = 5000)
        public void fixedDelayTask(){
            Task task = service.createTask("Fixed delay task");
            LOGGER.info(task.getDescription());
        }

        @Scheduled(fixedDelay = 5000)
        public void fixedDelayLongTask(){
            try{
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Task task = service.createTask("Fixed delay taking 3 seconds task");
            LOGGER.info(task.getDescription());
        }

        @Scheduled(fixedRate = 5000)
        @Async
        public void fixedRateLongAsyncTask(){
            try{
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Task task = service.createTask("Fixed rate taking 7 seconds asynchronous task");
            LOGGER.info(task.getDescription());
        }

        @Scheduled(fixedDelay = 5000, initialDelay = 20000)
        public void initialDelayTask(){
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Task task = service.createTask("Initial delay task");
            LOGGER.info(task.getDescription());
        }

        @Scheduled(fixedDelayString = "PT03S")
        public void anotherDelayFormatTask(){
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Task task = service.createTask("Another delay format task");
            LOGGER.info(task.getDescription());
        }


    @Scheduled(fixedDelayString = "${interval}")
    public void delayFromPropertyTask() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Task task = service.createTask("Delay from property task");
        LOGGER.info(task.getDescription());
    }

    @Scheduled(cron = "${cronolog}")
    public void cronFromPropertyTask() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Task task = service.createTask("Delay from property task");
        LOGGER.info(task.getDescription());
    }

    public static void executeScheduledTask(Task task){
        TaskScheduler scheduler = new DefaultManagedTaskScheduler();
        scheduler.schedule(() -> LOGGER.info(task.getDescription()),
                new CronTrigger("0,10,20,30,40,50 * * * * *"));
    }

    public static void executeScheduledTaskOnes(Task task){
        TaskScheduler scheduler = new DefaultManagedTaskScheduler();
        Instant executionTime = Instant.now().plusSeconds(10);
        scheduler.schedule(() -> LOGGER.info(task.getDescription()),
                executionTime);
    }


    ==================================================================================

     1 уровень сложности: 1. Реализовать вывод в консоль каждые 30 секунд списка последних пяти выполненных задач.
   Время выполнения предыдущей задачи не должно влиять на старт следующей.
   Создавать новую задачу и логировать ничего не нужно.
*/
    @Scheduled(fixedRateString = "PT30S")
    public void listOfLastFiveTasks(){

        List<Task> lastFive = new ArrayList<>();
            lastFive = service.getRepository()
                    .findAll().subList(Math.max(service.getRepository()
                            .findAll().size()-6, 0), service.getRepository()
                            .findAll().size()-1 );
        for (Task task : lastFive)
            {LOGGER.info(task.getDescription());}
    }
    /*
    ======================================================================
    2. Реализовать вывод в консоль последнего добавленного в БД товара.
    Вывод должен производиться в 15 и 45 секунд каждой минуты.
    Задача должна быть сохранена в БД.
    Вывод в консоль должен быть осуществлён через логирование поля description созданной задачи.
    Пример значения поля description - "Последний добавленный в БД продукт - Банан".

     */
    @Scheduled(cron = "15,45 * * * * *")
    public void showLastAddedArticle(){

        LOGGER.info(JpaProductService.lastTask.getDescription());
    }
}
