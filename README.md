# Job Scheduler
We implemented the reminder scheduler with Quartz as a standalone REST service.
Quartz is a richly featured, open source job scheduling library that can be integrated within virtually any Java application - from the smallest stand-alone application to the largest e-commerce system. Quartz can be used to create simple or complex schedules for executing tens, hundreds, or even tens-of-thousands of jobs; jobs whose tasks are defined as standard Java components that may execute virtually anything you may program them to do. The Quartz Scheduler includes many enterprise-class features, such as support for JTA transactions and clustering.

http://www.quartz-scheduler.org/documentation/quartz-2.2.2/tutorials/tutorial-lesson-02.html
## Testing:
curl --location 'http://localhost:8080/reminder/schedule' \
--header 'Content-Type: application/json' \
--data '{
"message": "Say hi...",
"dateTime": "2023-02-28T18:39:00",
"timeZone": "Asia/Kolkata"
}'