(function () {

    var c = document.createElement('canvas');
    var w = window.innerWidth;
    var h = window.innerHeight;
    c.width = w;
    c.height = h;
    c.style.left = "0px";
    c.style.top = "0px";
    c.style.position = "fixed";
    c.style.zIndex = "1024";
    c.style.pointerEvents = "none";
    document.body.appendChild(c);
    var ctx = c.getContext("2d");
    var elems = {};
    var scatter = parseFloat("0.5");
    var initScatter = parseInt("2");
    var elemSize = parseInt("5");
    var countElems = parseInt("50");
    var fallingSpeed = parseInt("0");
    var lifeSpan = parseFloat("2");
    var initOpac = parseFloat("0.8");
    var elemStepTime = 50;
    var soh1 = false;
    var soh2 = false;
    var lastDist = 0;
    var lastMoveT = 0;
    var color = "rgb(255,255,255)";
    var screenOffset = 0;

    function getXpos(ee)
    {

        return ee.clientX;
    }

    function getYpos(ee)
    {
        return ee.clientY;
    }

    function rnd_snd()
    {
        return (Math.random()*2-1)+(Math.random()*2-1)+(Math.random()*2-1)+(Math.random()*2-1)+(Math.random()*2-1);
    }
    function rnd(mean, stdev)
    {
        return Math.round(rnd_snd()*stdev+mean);
    }

    window.addEventListener('resize',function(e) {
        w = window.innerWidth;
        h = window.innerHeight;
        c.width = w;
        c.height = h;
    }, false);

    document.body.addEventListener('mousemove',function(e) {
        var x = getXpos(e);
        var y = getYpos(e);

        var moveX = e.movementX || e.webkitMovementX || e.mozMovementX || 0;
        var moveY = e.movementY || e.webkitMovementY || e.mozMovementY || 0;
        if (moveX==0 && moveY==0) return false;
        var dist = Math.sqrt(moveX*moveX+moveY*moveY);
        dist = dist+lastDist;
        var t = Date.now();

        var tmpOff = Math.abs(e.screenY-e.clientY);
        if (soh1 == tmpOff && soh2 == tmpOff) screenOffset = tmpOff;
        else if (soh1) soh2 = tmpOff;
        else soh1 = tmpOff;

        var iters = Math.floor(dist/(100/countElems));
        var i = 0
        while (dist >= (100/countElems))
        {
            var outKey = 'i1';

            var rot = 0;

            if (i>0) xStart = x - moveX * i/iters;
            else xStart = x;
            if (initScatter > 0) var xout = rnd(xStart,initScatter/2);
            else xout = xStart;
            if (i>0 && t-lastMoveT < 200) var fallingMod = (i/iters) * fallingSpeed * ((t-lastMoveT)/elemStepTime);
            else var fallingMod = 0;
            if (i>0) yStart = y - moveY * i/iters + fallingMod;
            else yStart = y;
            if (initScatter > 0) var yout = rnd(yStart,initScatter/2);
            else yout = yStart;
            var colorKey = 'i1';
            elems['i'+t+'d'+i] = {opac:100,x:xout,y:yout,t:t,rotation:rot,side:0,side2:0,elemkey:outKey,colorkey:colorKey,color:color,rainbow:0};
            dist = dist - (100/countElems);
            i++;
        }
        lastDist = dist;
        lastMoveT = t;
    }, false);

    var t = setInterval(function(){
        ctx.clearRect(0, 0, w, h);
        for (key in elems)
        {
            var el = elems[key];

            var t = Date.now()
            var mult = (t-el.t)/elemStepTime;

            var opac = el.opac-(5/lifeSpan)*mult;
            if (opac > 0)
            {
                el.t = t;
                el.opac = opac;
                el.y = el.y+fallingSpeed*mult;

                var tmpSize = Math.floor(Math.random()*(scatter*10+1))*2*mult;
                el.side = el.side+tmpSize-scatter*mult*10;
                el.x = el.x+el.side/10;


                var tmpSize2 = Math.floor(Math.random()*(scatter*10+1))*2*mult;
                el.side2 = el.side2+tmpSize2-scatter*mult*10;
                el.y = el.y+el.side2/10;

                ctx.fillStyle = color.replace('rgb', 'rgba').replace(')', ','+((el.opac/100)*initOpac)+')');
                ctx.beginPath();
                ctx.moveTo(el.x,el.y-elemSize/2);
                ctx.quadraticCurveTo(el.x,el.y,el.x+elemSize/2,el.y);
                ctx.quadraticCurveTo(el.x,el.y,el.x,el.y+elemSize/2);
                ctx.quadraticCurveTo(el.x,el.y,el.x-elemSize/2,el.y);
                ctx.quadraticCurveTo(el.x,el.y,el.x,el.y-elemSize/2);
                ctx.fill();
                elems[key] = el;
            }
            else delete elems[key];
        }
    }, elemStepTime);
})();