<!DOCTYPE html>
<html>
	
  <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
  <%@ page isELIgnored="false" %> 
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
  <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
  <head>
    <script
      src="https://code.jquery.com/jquery-3.6.3.min.js"
      integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
      crossorigin="anonymous"
    ></script>
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
    />
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <meta charset="UTF-8" />
    <title>User Profile</title>
    <style>
      .highlighter {
        display: inline-block;
        background-color: #a8a8a8;
        margin: 15px 0px;
        padding: 5px 10px;
        color: white;
      }

      .link {
        display: block;
        width: 100px;
        font-weight: 500;
      }
    </style>
  </head>
  <body>
    <nav class="navbar navbar-dark bg-primary justify-content-between">
      <a class="navbar-brand" href="/">Ticketing Gateway</a>
      <ul class="navbar-nav">
        <li class="nav-item dropdown">
          <a
            class="nav-link dropdown-toggle"
            href="#"
            id="navbarDropdownMenuLink"
            data-toggle="dropdown"
            aria-haspopup="true"
            aria-expanded="false"
          >
            <security:authorize access="isAuthenticated()">
              Welcome,<span
                id="username"
                class="font-weight-bold font-italic ml-1"
                ><security:authentication property="principal.username"
              /></span>
            </security:authorize>
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            <a class="dropdown-item" href="/home">Home</a>
            <a class="dropdown-item" href="/login?logout">Logout</a>
          </div>
        </li>
      </ul>
    </nav>

    <div class="container mb-4">
      <div class="row justify-content-between align-items-center mt-4">
        <h3 class="">Recent Bookings</h3>
        <div class="btn-group-toggle" data-toggle="buttons">
          <label class="btn btn-outline-success">
            <input type="checkbox" class="filter-checkbox" value="UPCOMING" />
            Upcoming
          </label>
          <label class="btn btn-outline-success">
            <input type="checkbox" class="filter-checkbox" value="COMPLETED" />
            Completed
          </label>
          <label class="btn btn-outline-success">
            <input type="checkbox" class="filter-checkbox" value="CANCELLED" />
            Cancelled
          </label>
        </div>
      </div>
      <div class="bookings-body">
        <div class="d-flex justify-content-center">
          <div id="booking-table" class="mt-3">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>BookingId</th>
                  <th class="d-none">HotelId</th>
                  <th>BookingDate</th>
                  <th>CheckInDate</th>
                  <th>CheckOutDate</th>
                  <th>CustomerMobile</th>
                  <th>Price</th>
                  <th>Status</th>
                  <th style="color: red; text-align: center" colspan="2">
                    Actions
                  </th>
                </tr>
              </thead>
              <tbody id="booking-table-body"></tbody>
            </table>
          </div>
        </div>
      </div>
      
      <security:authorize access="isAuthenticated()">
          Welcome
          <span id="username"
            ><security:authentication property="principal.username" />
          </span>
          <security:authorize access="hasAuthority('ADMIN')">
            <h3>Ankit Arora</h3>
          </security:authorize>
        </security:authorize>


      <!-- New Review Modal-->
      <div class="modal" id="reviewModal">
        <div class="modal-dialog modal-lg">
          <div class="modal-content">
			
            <!-- Modal Header -->
            <div class="modal-header">
              <h4 class="modal-title">Hotel Review</h4>
              <button type="button" class="close" data-dismiss="modal">
                &times;
              </button>
            </div>

            <!-- Modal body -->
            <div
              class="modal-body"
              id="reviewModal_modalBody"
              style="overflow: auto; height: 400px"
            >
              <div class="container">
                <!-- Question 1 -->
                <div class="row justify-content-between">
                  <p class="mr-2">
                    Question 1: How was the service? (Rate 1 to 5)
                  </p>
                  
                </div>  
              </div>
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
              <button id="review-submit" class="btn btn-primary">Submit</button>
              <button type="button" class="btn btn-danger" data-dismiss="modal">
                Close
              </button>
            </div>
          </div>
        </div>
      </div>

      <div class="modal" id="question-modal">
        <div class="modal-dialog">
          <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
              <h4 class="modal-title">You have Questions to be Answered!</h4>
              <button type="button" class="close" data-dismiss="modal">
                &times;
              </button>
            </div>

            <div class="modal-body" id="questionModal_body">
              <div class="form-group"></div>
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
              <button type="button" class="btn btn-danger" data-dismiss="modal">
                Close
              </button>
            </div>
          </div>
        </div>
      </div>

      <div class="modal" id="answer-modal">
        <div class="modal-dialog">
          <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
              <h4 class="modal-title">Please Answer!</h4>
              <button type="button" class="close" data-dismiss="modal">
                &times;
              </button>
            </div>

            <div class="modal-body" id="questionModal_body">
              <div id="question"></div>

              <div id="answer">
                <textarea
                  id="answer-text"
                  style="width: 100%; height: 100px"
                ></textarea>
                <div>
                  <button id="submit-answer" class="btn">Submit</button>
                </div>
              </div>
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
              <button type="button" class="btn btn-danger" data-dismiss="modal">
                Close
              </button>
            </div>
          </div>
        </div>
      </div>

      <div class="modal" id="successModal">
        <div class="modal-dialog">
          <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
              <h4 class="modal-title">Confirmed</h4>
              <button type="button" class="close" data-dismiss="modal">
                &times;
              </button>
            </div>

            <div class="modal-body" id="successModel_body">
              <h3>SUCCESS!!!</h3>
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
              <button type="button" class="btn btn-danger" data-dismiss="modal">
                Close
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <script src="./js/userProfile.js"></script>
  </body>
</html>
