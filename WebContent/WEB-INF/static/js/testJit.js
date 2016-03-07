var labelType, useGradients, nativeTextSupport, animate;

(function() {
  var ua = navigator.userAgent,
      iStuff = ua.match(/iPhone/i) || ua.match(/iPad/i),
      typeOfCanvas = typeof HTMLCanvasElement,
      nativeCanvasSupport = (typeOfCanvas == 'object' || typeOfCanvas == 'function'),
      textSupport = nativeCanvasSupport 
        && (typeof document.createElement('canvas').getContext('2d').fillText == 'function');
  //I'm setting this based on the fact that ExCanvas provides text support for IE
  //and that as of today iPhone/iPad current text support is lame
  labelType = (!nativeCanvasSupport || (textSupport && !iStuff))? 'Native' : 'HTML';
  nativeTextSupport = labelType == 'Native';
  useGradients = nativeCanvasSupport;
  animate = !(iStuff || !nativeCanvasSupport);
})();

var Log = {
  elem: false,
  write: function(text){
    if (!this.elem) 
      this.elem = document.getElementById('log');
    var text1 = text.replace(" >", ">当期账单金额(万元):").replace("当期:","");
    var text2 = text1.replace(" >", ">上月账单金额(万元):").replace("上期:","");
    var text3 = text2.replace(" >", ">去年同期账单金额(万元):").replace("同期:","");
    var text4 = text3.replace(" >", ">环比(%)：当期账单金额/上月账单金额-1:").replace("环比:","");
    var text5 = text4.replace(" >", ">同比(%)：当期账单金额/去年同期账单金额-1:").replace("同比:","");
    this.elem.innerHTML = text5;
    this.elem.style.left = (500 - this.elem.offsetWidth / 2) + 'px';
  }
};


function init(json){
    //初始化json数据
//  var json = "{id:\"node02\", name:\"总收入\", data:{}, children:[{id:\"node13\", name:\"个人客户总体收入<p style='color:red;'>第一层级</p>\", data:{}, children:[{id:\"node24\", name:\"总体语言收入\", data:{}, children:[{id:\"node35\", name:\"存量市场收入\", data:{}, children:[{id:\"node46\", name:\"总体MOU\", data:{}, children:[]}]}, {id:\"node37\", name:\"3.7\", data:{}, children:[{id:\"node48\", name:\"4.8\", data:{}, children:[]}, {id:\"node49\", name:\"4.9\", data:{}, children:[]}, {id:\"node410\", name:\"4.10\", data:{}, children:[]}, {id:\"node411\", name:\"4.11\", data:{}, children:[]}]}, {id:\"node312\", name:\"3.12\", data:{}, children:[{id:\"node413\", name:\"4.13\", data:{}, children:[]}]}, {id:\"node314\", name:\"3.14\", data:{}, children:[{id:\"node415\", name:\"4.15\", data:{}, children:[]}, {id:\"node416\", name:\"4.16\", data:{}, children:[]}, {id:\"node417\", name:\"4.17\", data:{}, children:[]}, {id:\"node418\", name:\"4.18\", data:{}, children:[]}]}, {id:\"node319\", name:\"3.19\", data:{}, children:[{id:\"node420\", name:\"4.20\", data:{}, children:[]}, {id:\"node421\", name:\"4.21\", data:{}, children:[]}]}]}, {id:\"node222\", name:\"2.22\", data:{}, children:[{id:\"node323\", name:\"3.23\", data:{}, children:[{id:\"node424\", name:\"4.24\", data:{}, children:[]}]}]}]}, {id:\"node125\", name:\"部门二<p style='color:red;'>测试测试测试测试</p>\", data:{}, children:[{id:\"node226\", name:\"2.26\", data:{}, children:[{id:\"node327\", name:\"3.27\", data:{}, children:[{id:\"node428\", name:\"4.28\", data:{}, children:[]}, {id:\"node429\", name:\"4.29\", data:{}, children:[]}]}, {id:\"node330\", name:\"3.30\", data:{}, children:[{id:\"node431\", name:\"4.31\", data:{}, children:[]}]}, {id:\"node332\", name:\"3.32\", data:{}, children:[{id:\"node433\", name:\"4.33\", data:{}, children:[]}, {id:\"node434\", name:\"4.34\", data:{}, children:[]}, {id:\"node435\", name:\"4.35\", data:{}, children:[]}, {id:\"node436\", name:\"4.36\", data:{}, children:[]}]}]}, {id:\"node237\", name:\"2.37\", data:{}, children:[{id:\"node338\", name:\"3.38\", data:{}, children:[{id:\"node439\", name:\"4.39\", data:{}, children:[]}, {id:\"node440\", name:\"4.40\", data:{}, children:[]}, {id:\"node441\", name:\"4.41\", data:{}, children:[]}]}, {id:\"node342\", name:\"3.42\", data:{}, children:[{id:\"node443\", name:\"4.43\", data:{}, children:[]}]}, {id:\"node344\", name:\"3.44\", data:{}, children:[{id:\"node445\", name:\"4.45\", data:{}, children:[]}, {id:\"node446\", name:\"4.46\", data:{}, children:[]}, {id:\"node447\", name:\"4.47\", data:{}, children:[]}]}, {id:\"node348\", name:\"3.48\", data:{}, children:[{id:\"node449\", name:\"4.49\", data:{}, children:[]}, {id:\"node450\", name:\"4.50\", data:{}, children:[]}, {id:\"node451\", name:\"4.51\", data:{}, children:[]}, {id:\"node452\", name:\"4.52\", data:{}, children:[]}, {id:\"node453\", name:\"4.53\", data:{}, children:[]}]}, {id:\"node354\", name:\"3.54\", data:{}, children:[{id:\"node455\", name:\"4.55\", data:{}, children:[]}, {id:\"node456\", name:\"4.56\", data:{}, children:[]}, {id:\"node457\", name:\"4.57\", data:{}, children:[]}]}]}, {id:\"node258\", name:\"2.58\", data:{}, children:[{id:\"node359\", name:\"3.59\", data:{}, children:[{id:\"node460\", name:\"4.60\", data:{}, children:[]}, {id:\"node461\", name:\"4.61\", data:{}, children:[]}, {id:\"node462\", name:\"4.62\", data:{}, children:[]}, {id:\"node463\", name:\"4.63\", data:{}, children:[]}, {id:\"node464\", name:\"4.64\", data:{}, children:[]}]}]}]}, {id:\"node165\", name:\"部门三\", data:{}, children:[{id:\"node266\", name:\"2.66\", data:{}, children:[{id:\"node367\", name:\"3.67\", data:{}, children:[{id:\"node468\", name:\"4.68\", data:{}, children:[]}, {id:\"node469\", name:\"4.69\", data:{}, children:[]}, {id:\"node470\", name:\"4.70\", data:{}, children:[]}, {id:\"node471\", name:\"4.71\", data:{}, children:[]}]}, {id:\"node372\", name:\"3.72\", data:{}, children:[{id:\"node473\", name:\"4.73\", data:{}, children:[]}, {id:\"node474\", name:\"4.74\", data:{}, children:[]}, {id:\"node475\", name:\"4.75\", data:{}, children:[]}, {id:\"node476\", name:\"4.76\", data:{}, children:[]}]}, {id:\"node377\", name:\"3.77\", data:{}, children:[{id:\"node478\", name:\"4.78\", data:{}, children:[]}, {id:\"node479\", name:\"4.79\", data:{}, children:[]}]}, {id:\"node380\", name:\"3.80\", data:{}, children:[{id:\"node481\", name:\"4.81\", data:{}, children:[]}, {id:\"node482\", name:\"4.82\", data:{}, children:[]}]}]}, {id:\"node283\", name:\"2.83\", data:{}, children:[{id:\"node384\", name:\"3.84\", data:{}, children:[{id:\"node485\", name:\"4.85\", data:{}, children:[]}]}, {id:\"node386\", name:\"3.86\", data:{}, children:[{id:\"node487\", name:\"4.87\", data:{}, children:[]}, {id:\"node488\", name:\"4.88\", data:{}, children:[]}, {id:\"node489\", name:\"4.89\", data:{}, children:[]}, {id:\"node490\", name:\"4.90\", data:{}, children:[]}, {id:\"node491\", name:\"4.91\", data:{}, children:[]}]}, {id:\"node392\", name:\"3.92\", data:{}, children:[{id:\"node493\", name:\"4.93\", data:{}, children:[]}, {id:\"node494\", name:\"4.94\", data:{}, children:[]}, {id:\"node495\", name:\"4.95\", data:{}, children:[]}, {id:\"node496\", name:\"4.96\", data:{}, children:[]}]}, {id:\"node397\", name:\"3.97\", data:{}, children:[{id:\"node498\", name:\"4.98\", data:{}, children:[]}]}, {id:\"node399\", name:\"3.99\", data:{}, children:[{id:\"node4100\", name:\"4.100\", data:{}, children:[]}, {id:\"node4101\", name:\"4.101\", data:{}, children:[]}, {id:\"node4102\", name:\"4.102\", data:{}, children:[]}, {id:\"node4103\", name:\"4.103\", data:{}, children:[]}]}]}, {id:\"node2104\", name:\"2.104\", data:{}, children:[{id:\"node3105\", name:\"3.105\", data:{}, children:[{id:\"node4106\", name:\"4.106\", data:{}, children:[]}, {id:\"node4107\", name:\"4.107\", data:{}, children:[]}, {id:\"node4108\", name:\"4.108\", data:{}, children:[]}]}]}, {id:\"node2109\", name:\"2.109\", data:{}, children:[{id:\"node3110\", name:\"3.110\", data:{}, children:[{id:\"node4111\", name:\"4.111\", data:{}, children:[]}, {id:\"node4112\", name:\"4.112\", data:{}, children:[]}]}, {id:\"node3113\", name:\"3.113\", data:{}, children:[{id:\"node4114\", name:\"4.114\", data:{}, children:[]}, {id:\"node4115\", name:\"4.115\", data:{}, children:[]}, {id:\"node4116\", name:\"4.116\", data:{}, children:[]}]}, {id:\"node3117\", name:\"3.117\", data:{}, children:[{id:\"node4118\", name:\"4.118\", data:{}, children:[]}, {id:\"node4119\", name:\"4.119\", data:{}, children:[]}, {id:\"node4120\", name:\"4.120\", data:{}, children:[]}, {id:\"node4121\", name:\"4.121\", data:{}, children:[]}]}, {id:\"node3122\", name:\"3.122\", data:{}, children:[{id:\"node4123\", name:\"4.123\", data:{}, children:[]}, {id:\"node4124\", name:\"4.124\", data:{}, children:[]}]}]}, {id:\"node2125\", name:\"2.125\", data:{}, children:[{id:\"node3126\", name:\"3.126\", data:{}, children:[{id:\"node4127\", name:\"4.127\", data:{}, children:[]}, {id:\"node4128\", name:\"4.128\", data:{}, children:[]}, {id:\"node4129\", name:\"4.129\", data:{}, children:[]}]}]}]}, {id:\"node1130\", name:\"部门四\", data:{}, children:[{id:\"node2131\", name:\"2.131\", data:{}, children:[{id:\"node3132\", name:\"3.132\", data:{}, children:[{id:\"node4133\", name:\"4.133\", data:{}, children:[]}, {id:\"node4134\", name:\"4.134\", data:{}, children:[]}, {id:\"node4135\", name:\"4.135\", data:{}, children:[]}, {id:\"node4136\", name:\"4.136\", data:{}, children:[]}, {id:\"node4137\", name:\"4.137\", data:{}, children:[]}]}]}, {id:\"node2138\", name:\"2.138\", data:{}, children:[{id:\"node3139\", name:\"3.139\", data:{}, children:[{id:\"node4140\", name:\"4.140\", data:{}, children:[]}, {id:\"node4141\", name:\"4.141\", data:{}, children:[]}]}, {id:\"node3142\", name:\"3.142\", data:{}, children:[{id:\"node4143\", name:\"4.143\", data:{}, children:[]}, {id:\"node4144\", name:\"4.144\", data:{}, children:[]}, {id:\"node4145\", name:\"4.145\", data:{}, children:[]}, {id:\"node4146\", name:\"4.146\", data:{}, children:[]}, {id:\"node4147\", name:\"4.147\", data:{}, children:[]}]}]}]}]}";
    //var json ="{id:\"node02\",name:\"总收入<div style='color:red;' title='当期账单金额' readonly >845412&nbsp&nbsp↑</div><div style='color:red;' title='上月账单金额' readonly >845412&nbsp&nbsp↑</div><div style='color:red;' title='去年同期账单金额' >845412&nbsp&nbsp↑</div><div style='color:red;' title='环比（%）：当期账单金额/上月账单金额-1' >845412&nbsp&nbsp↑</div><div style='color:red;' title='同比（%）：当期账单金额/去年同期账单金额-1' >845412&nbsp&nbsp↑</div>\",data:{},children:[{id:\"node13\",name:\"个人客户总体收入<div style='color:red;'>845412&nbsp&nbsp↑</div><div style='color:red;'>845412&nbsp&nbsp↑</div><div style='color:red;'>845412&nbsp&nbsp↑</div><div style='color:red;'>845412&nbsp&nbsp↑</div><div style='color:red;'>845412&nbsp&nbsp↑</div>\",data:{},children:[{id:\"node24\",name:\"总体语言收入\",data:{},children:[{id:\"node35\",name:\"存量市场收入\",data:{},children:[{id:\"node46\",name:\"总体MOU\",data:{},children:[]}]},{id:\"node37\",name:\"3.7\",data:{},children:[{id:\"node48\",name:\"4.8\",data:{},children:[]},{id:\"node49\",name:\"4.9\",data:{},children:[]},{id:\"node410\",name:\"4.10\",data:{},children:[]},{id:\"node411\",name:\"4.11\",data:{},children:[]}]},{id:\"node312\",name:\"3.12\",data:{},children:[{id:\"node413\",name:\"4.13\",data:{},children:[]}]},{id:\"node314\",name:\"3.14\",data:{},children:[{id:\"node415\",name:\"4.15\",data:{},children:[]},{id:\"node416\",name:\"4.16\",data:{},children:[]},{id:\"node417\",name:\"4.17\",data:{},children:[]},{id:\"node418\",name:\"4.18\",data:{},children:[]}]},{id:\"node319\",name:\"3.19\",data:{},children:[{id:\"node420\",name:\"4.20\",data:{},children:[]},{id:\"node421\",name:\"4.21\",data:{},children:[]}]}]},{id:\"node222\",name:\"2.22\",data:{},children:[{id:\"node323\",name:\"3.23\",data:{},children:[{id:\"node424\",name:\"4.24\",data:{},children:[]}]}]}]},{id:\"node125\",name:\"结算收入<p style='color:green;'>37542.12</p>\",data:{},children:[{id:\"node226\",name:\"2.26\",data:{},children:[{id:\"node327\",name:\"3.27\",data:{},children:[{id:\"node428\",name:\"4.28\",data:{},children:[]},{id:\"node429\",name:\"4.29\",data:{},children:[]}]},{id:\"node330\",name:\"3.30\",data:{},children:[{id:\"node431\",name:\"4.31\",data:{},children:[]}]},{id:\"node332\",name:\"3.32\",data:{},children:[{id:\"node433\",name:\"4.33\",data:{},children:[]},{id:\"node434\",name:\"4.34\",data:{},children:[]},{id:\"node435\",name:\"4.35\",data:{},children:[]},{id:\"node436\",name:\"4.36\",data:{},children:[]}]}]},{id:\"node237\",name:\"2.37\",data:{},children:[{id:\"node338\",name:\"3.38\",data:{},children:[{id:\"node439\",name:\"4.39\",data:{},children:[]},{id:\"node440\",name:\"4.40\",data:{},children:[]},{id:\"node441\",name:\"4.41\",data:{},children:[]}]},{id:\"node342\",name:\"3.42\",data:{},children:[{id:\"node443\",name:\"4.43\",data:{},children:[]}]},{id:\"node344\",name:\"3.44\",data:{},children:[{id:\"node445\",name:\"4.45\",data:{},children:[]},{id:\"node446\",name:\"4.46\",data:{},children:[]},{id:\"node447\",name:\"4.47\",data:{},children:[]}]},{id:\"node348\",name:\"3.48\",data:{},children:[{id:\"node449\",name:\"4.49\",data:{},children:[]},{id:\"node450\",name:\"4.50\",data:{},children:[]},{id:\"node451\",name:\"4.51\",data:{},children:[]},{id:\"node452\",name:\"4.52\",data:{},children:[]},{id:\"node453\",name:\"4.53\",data:{},children:[]}]},{id:\"node354\",name:\"3.54\",data:{},children:[{id:\"node455\",name:\"4.55\",data:{},children:[]},{id:\"node456\",name:\"4.56\",data:{},children:[]},{id:\"node457\",name:\"4.57\",data:{},children:[]}]}]},{id:\"node258\",name:\"2.58\",data:{},children:[{id:\"node359\",name:\"3.59\",data:{},children:[{id:\"node460\",name:\"4.60\",data:{},children:[]},{id:\"node461\",name:\"4.61\",data:{},children:[]},{id:\"node462\",name:\"4.62\",data:{},children:[]},{id:\"node463\",name:\"4.63\",data:{},children:[]},{id:\"node464\",name:\"4.64\",data:{},children:[]}]}]}]},{id:\"node165\",name:\"集客收入\",data:{},children:[{id:\"node266\",name:\"2.66\",data:{},children:[{id:\"node367\",name:\"3.67\",data:{},children:[{id:\"node468\",name:\"4.68\",data:{},children:[]},{id:\"node469\",name:\"4.69\",data:{},children:[]},{id:\"node470\",name:\"4.70\",data:{},children:[]},{id:\"node471\",name:\"4.71\",data:{},children:[]}]},{id:\"node372\",name:\"3.72\",data:{},children:[{id:\"node473\",name:\"4.73\",data:{},children:[]},{id:\"node474\",name:\"4.74\",data:{},children:[]},{id:\"node475\",name:\"4.75\",data:{},children:[]},{id:\"node476\",name:\"4.76\",data:{},children:[]}]},{id:\"node377\",name:\"3.77\",data:{},children:[{id:\"node478\",name:\"4.78\",data:{},children:[]},{id:\"node479\",name:\"4.79\",data:{},children:[]}]},{id:\"node380\",name:\"3.80\",data:{},children:[{id:\"node481\",name:\"4.81\",data:{},children:[]},{id:\"node482\",name:\"4.82\",data:{},children:[]}]}]},{id:\"node283\",name:\"2.83\",data:{},children:[{id:\"node384\",name:\"3.84\",data:{},children:[{id:\"node485\",name:\"4.85\",data:{},children:[]}]},{id:\"node386\",name:\"3.86\",data:{},children:[{id:\"node487\",name:\"4.87\",data:{},children:[]},{id:\"node488\",name:\"4.88\",data:{},children:[]},{id:\"node489\",name:\"4.89\",data:{},children:[]},{id:\"node490\",name:\"4.90\",data:{},children:[]},{id:\"node491\",name:\"4.91\",data:{},children:[]}]},{id:\"node392\",name:\"3.92\",data:{},children:[{id:\"node493\",name:\"4.93\",data:{},children:[]},{id:\"node494\",name:\"4.94\",data:{},children:[]},{id:\"node495\",name:\"4.95\",data:{},children:[]},{id:\"node496\",name:\"4.96\",data:{},children:[]}]},{id:\"node397\",name:\"3.97\",data:{},children:[{id:\"node498\",name:\"4.98\",data:{},children:[]}]},{id:\"node399\",name:\"3.99\",data:{},children:[{id:\"node4100\",name:\"4.100\",data:{},children:[]},{id:\"node4101\",name:\"4.101\",data:{},children:[]},{id:\"node4102\",name:\"4.102\",data:{},children:[]},{id:\"node4103\",name:\"4.103\",data:{},children:[]}]}]},{id:\"node2104\",name:\"2.104\",data:{},children:[{id:\"node3105\",name:\"3.105\",data:{},children:[{id:\"node4106\",name:\"4.106\",data:{},children:[]},{id:\"node4107\",name:\"4.107\",data:{},children:[]},{id:\"node4108\",name:\"4.108\",data:{},children:[]}]}]},{id:\"node2109\",name:\"2.109\",data:{},children:[{id:\"node3110\",name:\"3.110\",data:{},children:[{id:\"node4111\",name:\"4.111\",data:{},children:[]},{id:\"node4112\",name:\"4.112\",data:{},children:[]}]},{id:\"node3113\",name:\"3.113\",data:{},children:[{id:\"node4114\",name:\"4.114\",data:{},children:[]},{id:\"node4115\",name:\"4.115\",data:{},children:[]},{id:\"node4116\",name:\"4.116\",data:{},children:[]}]},{id:\"node3117\",name:\"3.117\",data:{},children:[{id:\"node4118\",name:\"4.118\",data:{},children:[]},{id:\"node4119\",name:\"4.119\",data:{},children:[]},{id:\"node4120\",name:\"4.120\",data:{},children:[]},{id:\"node4121\",name:\"4.121\",data:{},children:[]}]},{id:\"node3122\",name:\"3.122\",data:{},children:[{id:\"node4123\",name:\"4.123\",data:{},children:[]},{id:\"node4124\",name:\"4.124\",data:{},children:[]}]}]},{id:\"node2125\",name:\"2.125\",data:{},children:[{id:\"node3126\",name:\"3.126\",data:{},children:[{id:\"node4127\",name:\"4.127\",data:{},children:[]},{id:\"node4128\",name:\"4.128\",data:{},children:[]},{id:\"node4129\",name:\"4.129\",data:{},children:[]}]}]}]},{id:\"node1130\",name:\"高价值收入\",data:{},children:[{id:\"node2131\",name:\"2.131\",data:{},children:[{id:\"node3132\",name:\"3.132\",data:{},children:[{id:\"node4133\",name:\"4.133\",data:{},children:[]},{id:\"node4134\",name:\"4.134\",data:{},children:[]},{id:\"node4135\",name:\"4.135\",data:{},children:[]},{id:\"node4136\",name:\"4.136\",data:{},children:[]},{id:\"node4137\",name:\"4.137\",data:{},children:[]}]}]},{id:\"node2138\",name:\"2.138\",data:{},children:[{id:\"node3139\",name:\"3.139\",data:{},children:[{id:\"node4140\",name:\"4.140\",data:{},children:[]},{id:\"node4141\",name:\"4.141\",data:{},children:[]}]},{id:\"node3142\",name:\"3.142\",data:{},children:[{id:\"node4143\",name:\"4.143\",data:{},children:[]},{id:\"node4144\",name:\"4.144\",data:{},children:[]},{id:\"node4145\",name:\"4.145\",data:{},children:[]},{id:\"node4146\",name:\"4.146\",data:{},children:[]},{id:\"node4147\",name:\"4.147\",data:{},children:[]}]}]}]}]}";
    //end
	
    //A client-side tree generator 生成客户端树
    var getTree = (function() {
    	//设置起始几个节点展示
        var i = 0;
        return function(nodeId, level) {
          if(json==null){
        	  subtree = {};
          }  else {
              var subtree = eval('(' + json.replace(/id:\"([a-zA-Z0-9]+)\"/g,
              function(all, match) {
                return "id:\"" + match + "_" + i + "\""  
              }) + ')');
          }
//          var subtree = eval('(' + json.replace(/id:\"([a-zA-Z0-9]+)\"/g,
//          function(all, match) {
//            return "id:\"" + match + "_" + i + "\""  
//          }) + ')');
          $jit.json.prune(subtree, level);
          i++;
          //设置最后再次循环
//        return {
//            'id': nodeId,
//            'children': subtree.children
//        };
        };
    })();
    
    //Implement a node rendering function called 'nodeline' that plots a straight line
    //when contracting or expanding a subtree.
    //实现节点渲染函数nodeline,在收缩或展开的子树时绘制一条直线
    $jit.ST.Plot.NodeTypes.implement({
        'nodeline': {
          'render': function(node, canvas, animating) {
                if(animating === 'expand' || animating === 'contract') {
                  var pos = node.pos.getc(true), nconfig = this.node, data = node.data;
                  var width  = nconfig.width, height = nconfig.height;
                  var algnPos = this.getAlignedPos(pos, width, height);
                  var ctx = canvas.getCtx(), ort = this.config.orientation;
                  ctx.beginPath();
                  if(ort == 'left' || ort == 'right') {
                      ctx.moveTo(algnPos.x, algnPos.y + height / 2);
                      ctx.lineTo(algnPos.x + width, algnPos.y + height / 2);
                  } else {
                      ctx.moveTo(algnPos.x + width / 2, algnPos.y);
                      ctx.lineTo(algnPos.x + width / 2, algnPos.y + height);
                  }
                  ctx.stroke();
              } 
          }
        }
          
    });

    //init Spacetree 初始化Spacetree
    //Create a new ST instance 创建一个新的ST实例
    var st = new $jit.ST({
        'injectInto': 'infovis',
        //set duration for the animation 为动画设置时间
        duration: 800,
        //set animation transition type 设置动画过渡类型
        transition: $jit.Trans.Quart.easeInOut,
        //set distance between node and its children 设置节点及其子节点之间的最大距离
        levelDistance: 110,
        //set max levels to show. Useful when used with 设置显示的最大的树的层级
        //the request method for requesting trees of specific depth 在请求的特定深度的树时十分有用
        levelsToShow: 2,
        //set node and edge styles 设置节点和边缘样式
        //set overridable=true for styling individual 为个别样式设置重写覆盖
        //nodes or edges 节点或边
        Node: {
            height: 130,
            width: 120,
            //use a custom 使用自定义
            //node rendering function 节点渲染函数
            type: 'nodeline',
            color:'#DDDDDD',  //选中线色
            lineWidth: 2,
//            left : 420,
//            top : 345,
            align:"center",
            overridable: true
        },
        
        Edge: {
            type: 'bezier',
            lineWidth: 2,
            color:'#DDDDDD', //线色
            overridable: true
        },
        
        //Add a request method for requesting on-demand json trees. 
        //This method gets called when a node
        //is clicked and its subtree has a smaller depth
        //than the one specified by the levelsToShow parameter.
        //In that case a subtree is requested and is added to the dataset.
        //This method is asynchronous, so you can make an Ajax request for that
        //subtree and then handle it to the onComplete callback.
        //Here we just use a client-side tree generator (the getTree function).
        //添加一个请求方法，按需请求json树。
        //此方法被调用当一个节点被点击时且其子树中具有比levelsToShow参数所指定的更小的深度。
        //在这种情况下，一个子树被请求，并添加到数据集。
        //这种方法是异步的，这样就可以使一个Ajax请求的子树，然后使用onComplete回调函数进行处理。
        //这里我们只使用一个客户端树生成器（getTree功能）。
        request: function(nodeId, level, onComplete) {
          var ans = getTree(nodeId, level);
          onComplete.onComplete(nodeId, ans);  
        },
        
        onBeforeCompute: function(node){
            Log.write("当前选中: " + node.name);//点击时展示文本
        },
        
        onAfterCompute: function(){
            Log.write("");//点击节点展开完成后展示文本
        },

        //This method is called on DOM label creation.
        //Use this method to add event handlers and styles to
        //your node.
        //调用此方法为DOM创建标签。使用此方法将事件处理程序和样式添加到您的节点。
        onCreateLabel: function(label, node){//树状图的节点样式
            label.id = node.id;            
            label.innerHTML = node.name;
            label.onclick = function(){
                st.onClick(node.id);
            };
            //set label styles 设置标签样式
            var style = label.style;
            style.width = 130 + 'px';
            style.height = 120 + 'px';            
            style.cursor = 'pointer';
            style.color = '#000';
//            style.left = 420 + 'px'; 
//            style.top = 345 + 'px';
            //style.backgroundColor = '#1a1a1a';
            style.marginLeft = -10 + 'px';
            style.marginTop = 15 + 'px';
            style.fontSize = '0.5em';//字体
            style.textAlign= 'center';
            style.textDecoration = 'none';
            style.paddingTop = '1px';
            style.backgroundColor='#F0F0F0';  //背景颜色
            style.border='#F0F0F0 1px solid';//边线
            style.fontFamily='Arial, Helvetica, sans-serif';
			
        },
        
        //This method is called right before plotting
        //a node. It's useful for changing an individual node
        //style properties before plotting it.
        //The data properties prefixed with a dollar
        //sign will override the global node style properties.
        //这个方法在绘制一个节点之前被正确调用。这对绘制之前，改变单个节点的样式属性非常有用。前面有一个美元符号的数据属性将覆盖全局节点的样式属性。
        onBeforePlotNode: function(node){
            //add some color to the nodes in the path between the
            //root node and the selected node.
            //在根节点到选定的节点之间的路径上给节点添加一些颜色。
            if (node.selected) {
                node.data.$color = "#ff7";
            }
            else {
                delete node.data.$color;
            }
        },
        
        //This method is called right before plotting
        //an edge. It's useful for changing an individual edge
        //style properties before plotting it.
        //Edge data proprties prefixed with a dollar sign will
        //override the Edge global style properties.
        //此方法绘制一条边前才调用。这对绘制之前改变单个边样式属性非常有用。前面有一个美元符号将覆盖边缘全局样式属性EDGE数据的属性。
        onBeforePlotLine: function(adj){
            if (adj.nodeFrom.selected && adj.nodeTo.selected) {
                adj.data.$color = "#666";
                adj.data.$lineWidth = 3;
            }
            else {
                delete adj.data.$color;
                delete adj.data.$lineWidth;
            }
        }
    });

    //load json data 读取json数据
    st.loadJSON(eval( '(' + json + ')' ));
    //compute node positions and layout 计算节点的位置和布局
    st.compute();
    //optional: make a translation of the tree
    st.geom.translate(new $jit.Complex(-200, 0), "current");
    //emulate a click on the root node. 模拟根节点上的点击事件
    st.onClick(st.root);
    //end
    //Add event handlers to switch spacetree orientation. 添加事件处理程序切换spacetree树展示的方向
   function get(id) {
      return document.getElementById(id);  
    };

    var top = get('r-top'), 
    left = get('r-left'), 
    bottom = get('r-bottom'), 
    right = get('r-right');
    
    function changeHandler() {
        if(this.checked) {
            top.disabled = bottom.disabled = right.disabled = left.disabled = true;
            st.switchPosition(this.value, "animate", {
                onComplete: function(){
                    top.disabled = bottom.disabled = right.disabled = left.disabled = false;
                }
            });
        }
    };
    
    top.onchange = left.onchange = bottom.onchange = right.onchange = changeHandler;
    //end

}