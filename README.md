# filemanager
correvate test file manager for upload and downloading files
application will be running on port 9090
build your docker image using the command: docker build --tag=filemanager:latest .  
run your container using the command: docker run -p9090:9090 filemanager:latest

Please name the steps you would consider when defining a SDLC for a SaaS application. Provide a brief description of what is covered by each step and the tools you’d use/setup to facilitate it.
1. Planning: This step requires understanding the scope of the problem, user stories, resources, time and cost required to provide a solution.
2. System Analysis and requirement: We consider the functional requirements(features) of the solution and analyse the need of the user. SDLC Methodologies(Agile Scrum, Waterfall, Kaban) for implementing the solution is also decided here. Documentation of the use cases is key. Notion, an online tool for documentation and collaboration is a good way of documenting and updating
3. System Design: Design sessions are set up to discuss the architechtural specifications of the solution and the appropriate technology that satisify the proposed system. Choice of Database (NoSQL vs SQL), design patterns, cloud solutions vs on-premise. UML Diagrams such as class, sequence, activity diagrams are created to provide perspectie of the solutions, Figma can be used to create UI Mock ups and Business process tools such as bonita can be used to create a work flow. Jira for task monitoring
4. Developement: This is the start of implementation of architechtural specifications. Version control and repository of choice to be used. CI/CD integration to staging server for testing. Logging using sentry to give real time information in production when errors occur
5. Integration and testing: Here Quality Assurance test the features to determine if business needs are met. Vulnerabilty and Penetration Test could be carried out using veracode, checkmarx, Performance test Uisng JMeter
6. Production: This stage requires setting up of the staging and production environment, migration of data and components. Database migration tools. server setup using terraform
7. Operations and maintenance: performance tunning of system and adding new capabilities. 

