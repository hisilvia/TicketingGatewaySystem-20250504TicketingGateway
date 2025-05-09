﻿#Project: Ticking Gateway System
Topic:
•	Backend: Java Spring Boot, Spring Security, Spring Data JPA, ActiveMQ, JavaMailSender
•	Frontend: HTML/CSS, jQuery, AJAX
•	Database: MySQL/Oracle
•	Other Tools: Postman, Git/GitHub, iText PDF, cron scheduler

Week 1: Project Setup & Basic Architecture

Goals:
•	Understand microservices architecture
•	Set up base repositories and services
•	Create core entities (Ticket, Employee, Role, TicketHistory)

Tasks:
•	 Initialize GitHub repo and project structure (monorepo or per microservice)
•	 Create Employee and Role entities (with @ManyToMany)
•	 Implement Spring Security and login form
•	 Set up database (MySQL) and connect using Spring JPA
•	 Build basic login/authentication flow
•	 Setup microservices:
  o	Ticketing Gateway (UI/Frontend)
  o	Ticket Microservice (CRUD + history)
  o	Notification Microservice (basic skeleton)
•	 Develop simple frontend with login and dashboard per role

Deliverables:
•	Authenticated login for USER, MANAGER, ADMIN
•	Database tables created and connected
•	GitHub repo with initial commits
________________________________________
Week 2: Ticket Workflow Implementation

Goals:
•	Implement CRUD ticket operations
•	Enable approval, rejection, resolution, and viewing of tickets

Tasks:
•	 Ticket creation from UI with file upload support
•	 Save ticket info via REST API to Ticket Microservice
•	 Implement approval/rejection by MANAGER
•	 Ticket resolution by ADMIN
•	 Reopen/close functionality
•	 Create endpoints for ticket history viewing

Deliverables:
•	Fully working ticket lifecycle in database
•	UI components for creating, viewing, and updating tickets
•	REST APIs with basic test coverage (Postman or Swagger)
________________________________________
Week 3: Notifications, Automation, and Messaging

Goals:
•	Implement automated emails and scheduled tasks
•	Set up inter-microservice communication using ActiveMQ

Tasks:
•	 Send email on ticket creation (SimpleMailMessage)
•	 CRON job: Check tickets pending >7 days → notify manager
•	 Generate dynamic PDFs (iText) on ticket resolution
•	 Send PDF via MimeMessage email
•	 Implement ActiveMQ setup:
  o	Producer in Notification Service
  o	Consumer in Ticket Service
•	 Auto-close tickets after 5 days of unresolved "Resolved" status

Deliverables:
•	ActiveMQ working with Ticket & Notification services
•	Scheduled tasks running via cron
•	Functional email sending with PDF attachment
________________________________________
Week 4: UI Polishing, Testing & Documentation

Goals:
•	Make the UI clean and functional
•	Write documentation and handle edge cases
•	Conduct testing and code cleanup

Tasks:
•	 Frontend improvements (status colors, modals, alerts)
•	 Display ticket statuses visually
•	 Validate form inputs and handle exceptions
•	 Unit tests for Ticket and Notification services
•	 Write README with setup, architecture, and screenshots
•	 Prepare a project report or demo script

Deliverables:
•	Fully working system with login, ticket lifecycle, and notifications
•	Polished UI for users and admins
•	GitHub repo with documentation and code


 //********************************************************************
…or echo "# TicketingGatewaySystem-20250504TicketingGateway" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/hisilvia/TicketingGatewaySystem-20250504TicketingGateway.git
git push -u origin main

…or push an existing repository from the command line
git remote add origin https://github.com/hisilvia/TicketingGatewaySystem-20250504TicketingGateway.git
git branch -M main
git push -u origin main
