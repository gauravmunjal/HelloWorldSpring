function timeDifference(current, previous) {

    var msPerMinute = 60 * 1000;
    var msPerHour = msPerMinute * 60;
    var msPerDay = msPerHour * 24;
    var msPerMonth = msPerDay * 30;
    var msPerYear = msPerDay * 365;

    var elapsed = current - previous;

    if (elapsed < msPerMinute) {
        return  'few seconds ago';
    }

    else if (elapsed < msPerHour) {
        return Math.round(elapsed/msPerMinute) + ' minutes ago';
    }

    else if (elapsed < msPerDay ) {
        return Math.round(elapsed/msPerHour ) + ' hours ago';
    }

    else if (elapsed < msPerMonth) {
        return 'approximately ' + Math.round(elapsed/msPerDay) + ' days ago';
    }

    else if (elapsed < msPerYear) {
        return 'approximately ' + Math.round(elapsed/msPerMonth) + ' months ago';
    }

    else {
        return 'approximately ' + Math.round(elapsed/msPerYear ) + ' years ago';
    }
}

function getJsTimestamp( timeString ) {
    // split the mssql timestamp, and return it so that we
    // can create a date in javascript
    var arrMssqldate = timeString.split( ' ' );
    var arrDate = arrMssqldate[0].split( '-', 3 );
    var arrTime = arrMssqldate[1].split( ':', 2);

    var timeObject = new Object;
    timeObject.year = arrDate[0];
    timeObject.month = arrDate[1];
    timeObject.day = arrDate[2];
    timeObject.hour = arrTime[0];
    timeObject.minute = arrTime[1];
    timeObject.second = '00';

    return timeObject;

}
