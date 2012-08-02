<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <title>Minti @${user.username}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
        body {
            padding-top: 60px;
            padding-bottom: 40px;
            padding-left: 40px;
            padding-right: 40px;
            background-color:#ECF8E0;
        }
        .sidebar-nav {
            padding: 9px 0;
        }
        .tweet {
            padding-top: 5px;
            padding-bottom: 10px;
            color:green;
            border-style:outset;
            background-color:#E0F8E0;
        }
        .tweetheader {
            padding-left: 5px;
            padding-top: 5px;
            padding-bottom: 10px;
            color:black;
            font-family:"Times New Roman";
            font-size:25px;
        }
        .tweettext {
            padding-left: 5px;
            padding-top: 5px;
            padding-bottom: 5px;
            color:black;
            font-family:"Times New Roman";
            font-size:20px;
        }
        .time {
            float:right;
            padding-right: 5px;

        }
    </style>
    <link href="../assets/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
    <link rel="stylesheet" href="static/css/bootstrap.css">
    <script type="text/javascript" src="/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/ejs_production.js"></script>
    <script type="text/javascript" src="/static/js/tweets.js"></script>
    <script type="text/javascript" src="/static/js/time.js"></script>
    <!--<script type="text/javascript">
        function addTweet(form) {
            $.post('/tweet/create.json', $(form).serialize(),function(data) {
                var tweetItemLI = $(new EJS({url: '/static/ejs/tweet.ejs'}).render(data)).data("id", data.id);
                $('#tweetList').prepend(tweetItemLI);
            });
        }           </script>
        <script type="text/javascript">
        function appendItem(data) {
            var tweetItemLI = $(new EJS({url: '/static/ejs/tweet.ejs'}).render(data)).data("id", data.id);
            $('#tweetList').append(tweetItemLI);
        }
        </script>        -->

</head>

<body>

<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#">Minti</a>
            <div class="btn-group pull-right">
                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="icon-user"></i> ${user.name}
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="/user/${user.username}">Profile</a></li>
                    <li class="divider"></li>
                    <li><a href="/user/logout">Logout</a></li>
                </ul>
            </div>
            <div class="nav-collapse">
                <ul class="nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="/user/logout">Logout</a></li>
                    <li><a href="#contact">Contact</a></li>
                </ul>
                <form class="navbar-search pull-left" action="">
                    <input type="text" class="search-query span2" placeholder="Search Mintees">
                </form>
            </div><!--/.nav-collapse -->
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span2">
            <div class="well sidebar-nav">
                <ul class="nav nav-list">

                    <h2>@${user.name}</h2>
                    <p>We are here to create a dent in the universe, otherwise, why even be here.</p>
                    <p><a class="btn btn-primary btn-small">View my profile page&raquo;</a></p>

                </ul>
            </div><!--/.well -->
            <div class="well sidebar-nav">
                <ul class="nav nav-list">

                    <h2>Trending</h2>
                    <p>We are here to create a dent in the universe, otherwise, why even be here.</p>
                    <p><a class="btn btn-primary btn-small">View my profile page&raquo;</a></p>

                </ul>
            </div><!--/.well -->
        </div><!--/span-->
        <div class="span10">
            <div class="page-header">
                <h1>Mintees</h1>
            </div>
            <div class="row-fluid">
                <div class="span10">
                    <form action="/user/getNewTweet.json" onsubmit="displayNew(this);return false">
                        <input type="submit" style="display:none" value="you have new tweets!" id="updateBtn"/>
                    </form>
                    <form action="/tweet/create.json" onsubmit="addTweet(this); return false;">
                    Add New Tweet:
                    <textarea rows="3" cols="32" name="tweet"> Tweet! </textarea>
                    <input type="submit" value="Tweet"/>
                    </form>
                </div>
            </div>

            <div id="tweetList">





                <c:forEach var='tweet' items='${list}'>
                    <script type="text/javascript">
                        appendItem({id:${tweet.id}, tweet:'${tweet.tweet}', username:'${tweet.username}', pushtime:'${tweet.pushtime}'})
                    </script>
                </c:forEach>

            </div>







            <hr>

            <footer>
                <p>&copy; Company 2012</p>
            </footer>

        </div><!--/.fluid-container-->
        </div>
    </div>

        <!-- Le javascript
       ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="../assets/js/jquery.js"></script>
        <script src="../assets/js/bootstrap-transition.js"></script>
        <script src="../assets/js/bootstrap-alert.js"></script>
        <script src="../assets/js/bootstrap-modal.js"></script>
        <script src="../assets/js/bootstrap-dropdown.js"></script>
        <script src="../assets/js/bootstrap-scrollspy.js"></script>
        <script src="../assets/js/bootstrap-tab.js"></script>
        <script src="../assets/js/bootstrap-tooltip.js"></script>
        <script src="../assets/js/bootstrap-popover.js"></script>
        <script src="../assets/js/bootstrap-button.js"></script>
        <script src="../assets/js/bootstrap-collapse.js"></script>
        <script src="../assets/js/bootstrap-carousel.js"></script>
        <script src="../assets/js/bootstrap-typeahead.js"></script>

</body>
</html>
