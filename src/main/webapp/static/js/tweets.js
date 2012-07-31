
function addTweet(form) {
    $.post('/tweet/create.json', $(form).serialize(),function(data) {
//                var tweetItemLI = $(new EJS({url: '/static/ejs/tweet.ejs'}).render(data)).data("tweetID", data.tweet_id);
//                $('#tweetList').prepend(tweetItemLI);
    });
}

function appendItem(data) {
    var month_names_short= ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
    var timeObject = getJsTimestamp( String(data.pushtime) );
    var stamp =  new Date(timeObject.year, Number(timeObject.month)-1, timeObject.day, timeObject.hour, timeObject.minute, timeObject.second);
    //alert(jQuery.timeago(stamp));
    var timeDiff= timeDifference((new Date()).getTime(),stamp.getTime());
    data["timeDiff"]=timeDiff;
    data.pushtime =month_names_short[Number(timeObject.month)-1]+" "+ timeObject.day +" " + timeObject.year + " at " + timeObject.hour+":"+timeObject.minute;

    var tweetItemLI = $(new EJS({url: '/static/ejs/tweet.ejs'}).render(data)).data("id", data.id);
    $('#tweetList').append(tweetItemLI);
}

function displayNew(form){
    $.post('/tweet/getNewTweet.json', $(form).serialize(),function(data) {

        $('#updateBtn').hide();
        for(i=0;i<data.val;i++){
//                var tweetItemLI = $(new EJS({url: '/static/ejs/tweet.ejs'}).render(data.List[i])).data("tweetID", (data.List[i]).tweet_id);
//                $('#tweetList').prepend(tweetItemLI);

            var temp=data.List[i];

            var month_names_short= ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
            var timeObject = getJsTimestamp( String(temp.pushtime) );
            var stamp =  new Date(timeObject.year, Number(timeObject.month)-1, timeObject.day, timeObject.hour, timeObject.minute, timeObject.second);
            var timeDiff= timeDifference((new Date()).getTime(),stamp.getTime());
            temp["timeDiff"]=timeDiff;
            //alert(jQuery.timeago(new Date()));
            temp.pushtime =month_names_short[Number(timeObject.month)-1]+" "+ timeObject.day +" " + timeObject.year + " at " + timeObject.hour+":"+timeObject.minute;
            var tweetItemLI = $(new EJS({url: '/static/ejs/tweet.ejs'}).render(temp)).data("tweetID", (data.List[i]).id);
            $('#tweetList').prepend(tweetItemLI);

            appendItem(temp);

        }



    });
}

setInterval(function(){
    $.post("/tweet/updates.json", function(result) {
        if(result.value=="yes"){
            $('#updateBtn').attr("value", "you have "+ result.number + " new tweets!");
            $('#updateBtn').show();

        }
    });
}, 2000);

function getMoreTweets(form){
    $.post('/tweet/getMoreTweets.json', $(form).serialize(),function(data) {
        for(i=0;i<data.val;i++){
            appendItem(data.List[i]);
        }
        if(data.val==0) $('#moreTweetsBtn').hide();

    });
}