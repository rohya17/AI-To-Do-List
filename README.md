# AI-To-Do-List - Asking AI to manage my to do list 
Demonstrating Spring AI functions - Custom java functions to manage real time data

This project uses llama3.2 locally to manage to do list. 
1. To run this project you should have llama3.2 installed and running on your machine
2. Run databse query to create table
   CREATE TABLE IF NOT EXISTS public.todolist
   (
        id serial NOT NULL ,
        task text COLLATE pg_catalog."default" NOT NULL,
        deadline date NOT NULL,
        completed boolean NOT NULL DEFAULT false,
        CONSTRAINT todolist_pkey PRIMARY KEY (id)
   );
4. Run project and enjoy, you can find swagger ui on port 8080

5. Tested examples
  a. asking to add task
![image](https://github.com/user-attachments/assets/6f8ebdc0-3b5a-4095-93a9-5fc293546fc6)
  b. tasks added to database 
![image](https://github.com/user-attachments/assets/fd0cd8c2-e3de-482a-b1e1-e74a4cf23597)
  c. asking to list all tasks
![image](https://github.com/user-attachments/assets/7d482b82-c6d3-4e4d-b28b-113157afef29)
  d. asking to mark task complete
![image](https://github.com/user-attachments/assets/620ad412-523d-42f9-b470-84b7a7d95578)
  e. asking to remove task
![image](https://github.com/user-attachments/assets/d496d674-bb4d-4907-9368-ed86df9e7101)
  and task removed from database also
![image](https://github.com/user-attachments/assets/4f380a43-7538-4661-b4a6-d907df78f4d6)

