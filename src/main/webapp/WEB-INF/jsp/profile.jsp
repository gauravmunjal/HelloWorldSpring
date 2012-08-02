<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="static/css/bootstrap.css">
    <script type="text/javascript" src="/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/ejs_production.js"></script>
    <script type="text/javascript" src="/static/js/jquery.timeago.js"></script>
    <script type="text/javascript" src="/static/js/time.js"></script>
    <script type="text/javascript" src="/static/js/tweets.js"></script>
</head>
<body>



Hello <a href ="/user/loggedon/${sessionScope.userName}">${sessionScope.userName}</a>  <a href="/user/logout">Logout</a>
<h2><a href="/home">Homepage</a></h2>


<h1>Tweet Tweet!</h1>


<form action="/user/getNewTweet.json" onsubmit="displayNew(this);return false">
    <input type="submit" style="display:none" value="you have new tweets!" id="updateBtn"/>
</form>


<form action="/tweet/create.json" onsubmit="addTweet(this); return false;">
    Add New Tweet:
    <textarea rows="3" cols="32" name="description"> Tweet! </textarea>
    <input type="submit" value="Tweet"/>
</form>


<ul id="tweetList">

    <c:forEach var='item' items='${List}'>

        <script type="text/javascript">
            appendItem({tweet_id:${item.tweet_id}, description:'${item.description}', userName:'${item.userName}', time:'${item.time}'})
        </script>
    </c:forEach>

</ul>

<form action="/user/getMoreTweets.json" onsubmit="getMoreTweets(this);return false">
    <input type="submit"  value="Load More Tweets" id="moreTweetsBtn"/>
</form>

</body>
</html>