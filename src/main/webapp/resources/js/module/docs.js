Ext.BLANK_IMAGE_URL = 'resources/images/s.gif';

Docs = {};
Ext.mainScreem = null;

ApiPanel = function() {
    ApiPanel.superclass.constructor.call(this, {
        id:'api-tree',
        region:'west',
        split:true,
        header: false,
        width: 230,
        minSize: 175,
        maxSize: 500,
        collapsible: true,
        margins:'0 0 5 5',
        cmargins:'0 0 0 0',
        rootVisible:false,
        lines:false,
        autoScroll:true,
        animCollapse:false,
        animate: false,
        collapseMode:'mini',
        loader: new Ext.tree.TreeLoader({
			preloadChildren: true,
			clearOnLoad: false
		}),
        root: new Ext.tree.AsyncTreeNode({
            text:'Ext JS',
            id:'root',
            expanded:true,
            children:[Docs.classData]
         }),
        collapseFirst:false
    });
    // no longer needed!
    //new Ext.tree.TreeSorter(this, {folderSort:true,leafAttr:'isClass'});

    this.getSelectionModel().on('beforeselect', function(sm, node){
        return node.isLeaf();
    });
};

Ext.extend(ApiPanel, Ext.tree.TreePanel, {
    initComponent: function(){
        this.hiddenPkgs = [];
        Ext.apply(this, {
            tbar:[ ' ',
			new Ext.form.TextField({
				width: 200,
				emptyText:'Find a Class',
                enableKeyEvents: true,
				listeners:{
					render: function(f){
                    	this.filter = new Ext.tree.TreeFilter(this, {
                    		clearBlank: true,
                    		autoClear: true
                    	});
					},
                    keydown: {
                        fn: this.filterTree,
                        buffer: 350,
                        scope: this
                    },
                    scope: this
				}
			}), ' ', ' ',
			{
                iconCls: 'icon-expand-all',
				tooltip: 'Expand All',
                handler: function(){ this.root.expand(true); },
                scope: this
            }, '-', {
                iconCls: 'icon-collapse-all',
                tooltip: 'Collapse All',
                handler: function(){ this.root.collapse(true); },
                scope: this
            }]
        })
        ApiPanel.superclass.initComponent.call(this);
    },
	filterTree: function(t, e){
		var text = t.getValue();
		Ext.each(this.hiddenPkgs, function(n){
			n.ui.show();
		});
		if(!text){
			this.filter.clear();
			return;
		}
		this.expandAll();
		
		var re = new RegExp('^' + Ext.escapeRe(text), 'i');
		this.filter.filterBy(function(n){
			return !n.attributes.isClass || re.test(n.text);
		});
		
		// hide empty packages that weren't filtered
		this.hiddenPkgs = [];
                var me = this;
		this.root.cascade(function(n){
			if(!n.attributes.isClass && n.ui.ctNode.offsetHeight < 3){
				n.ui.hide();
				me.hiddenPkgs.push(n);
			}
		});
	},
    selectClass : function(cls){
        if(cls){
            var parts = cls.split('.');
            var last = parts.length-1;
            var res = [];
            var pkg = [];
            for(var i = 0; i < last; i++){ // things get nasty - static classes can have .
                var p = parts[i];
                var fc = p.charAt(0);
                var staticCls = fc.toUpperCase() == fc;
                if(p == 'Ext' || !staticCls){
                    pkg.push(p);
                    res[i] = 'pkg-'+pkg.join('.');
                }else if(staticCls){
                    --last;
                    res.splice(i, 1);
                }
            }
            res[last] = cls;

            this.selectPath('/root/apidocs/'+res.join('/'));
        }
    }
});


DocPanel = Ext.extend(Ext.Panel, {
    closable: true,
    autoScroll:true,
    
    initComponent : function(){
        var ps = this.cclass.split('.');
        this.title = ps[ps.length-1];
        Ext.apply(this,{
            tbar: ['->',{
                text: 'Config Options',
                handler: this.scrollToMember.createDelegate(this, ['configs']),
                iconCls: 'icon-config'
            },'-',{
                text: 'Properties',
                handler: this.scrollToMember.createDelegate(this, ['props']),
                iconCls: 'icon-prop'
            }, '-',{
                text: 'Methods',
                handler: this.scrollToMember.createDelegate(this, ['methods']),
                iconCls: 'icon-method'
            }, '-',{
                text: 'Events',
                handler: this.scrollToMember.createDelegate(this, ['events']),
                iconCls: 'icon-event'
            }, '-',{
                text: 'Direct Link',
                handler: this.directLink,
                scope: this,
                iconCls: 'icon-fav'
            }, '-',{
                tooltip:'Hide Inherited Members',
                iconCls: 'icon-hide-inherited',
                enableToggle: true,
                scope: this,
                toggleHandler : function(b, pressed){
                     this.body[pressed ? 'addClass' : 'removeClass']('hide-inherited');
                }
            }, '-', {
                tooltip:'Expand All Members',
                iconCls: 'icon-expand-members',
                enableToggle: true,
                scope: this,
                toggleHandler : function(b, pressed){
                    this.body[pressed ? 'addClass' : 'removeClass']('full-details');
                }
            }]
        });
        DocPanel.superclass.initComponent.call(this);
    },

    directLink : function(){
        var link = String.format(
            "<a href=\"{0}\" target=\"_blank\">{0}</a>",
            document.location.href+'?class='+this.cclass
        );
        Ext.Msg.alert('Direct Link to ' + this.cclass,link);
    },
    
    scrollToMember : function(member){
        var el = Ext.fly(this.cclass + '-' + member);
        if(el){
            var top = (el.getOffsetsTo(this.body)[1]) + this.body.dom.scrollTop;
            this.body.scrollTo('top', top-25, {duration:0.75, callback: this.hlMember.createDelegate(this, [member])});
        }
    },

	scrollToSection : function(id){
		var el = Ext.getDom(id);
		if(el){
			var top = (Ext.fly(el).getOffsetsTo(this.body)[1]) + this.body.dom.scrollTop;
			this.body.scrollTo('top', top-25, {duration:0.5, callback: function(){
                Ext.fly(el).next('h2').pause(0.2).highlight('#8DB2E3', {attr:'color'});
            }});
        }
	},

    hlMember : function(member){
        var el = Ext.fly(this.cclass + '-' + member);
        if(el){
            if (tr = el.up('tr')) {
                tr.highlight('#cadaf9');
            }
        }
    }
});

var tools = [{
        id:'gear',
        handler: function(){
            Ext.Msg.alert('Message', 'The Settings tool was clicked.');
        }
    },{
        id:'close',
        handler: function(e, target, panel){
            panel.ownerCt.remove(panel, true);
        }
    }];

MainPanel = function(){
	
	this.searchStore = new Ext.data.Store({
        proxy: new Ext.data.ScriptTagProxy({
            url: 'http://extjs.com/playpen/api.php'
        }),
        reader: new Ext.data.JsonReader({
	            root: 'data'
	        }, 
			['cls', 'member', 'type', 'doc']
		),
		baseParams: {},
        listeners: {
            'beforeload' : function(){
                this.baseParams.qt = Ext.getCmp('search-type').getValue();
            }
        }
    }); 
	
    MainPanel.superclass.constructor.call(this, {
        id:'doc-body',
        region:'center',
        margins:'0 5 5 0',
        resizeTabs: true,
        minTabWidth: 135,
        tabWidth: 135,
        plugins: new Ext.ux.TabCloseMenu(),
        enableTabScroll: true,
        activeTab: 0,

        items:{
            xtype:'portal',
            title: 'API Home',
            iconCls:'icon-docs',
            //region:'center',
            margins:'35 5 5 0',
            items:[{
                columnWidth:.33,
                style:'padding:10px 0 10px 10px',
                items:[{
                    title: 'Grid in a Portlet',
                    layout:'fit',
                    tools: tools,
                    items: new SampleGrid([0, 2, 3])
                },{
                    title: 'Another Panel 1',
                    tools: tools,
                    contentEl: 'column4ddiv'
                }]
            },{
                columnWidth:.33,
                style:'padding:10px 0 10px 10px',
                items:[{
                    title: 'Panel 2',
                    tools: tools,
                    contentEl: 'chartdiv'
                    //html: '<div id=chartdiv ><div>'
                },{
                    title: 'Another Panel 2',
                    //tools: tools,
                    html: ''
                }]
            },{
                columnWidth:.33,
                style:'padding:10px 0 10px 10px',
                items:[{
                    title: 'Panel 31',
                    tools: tools,
                    contentEl: 'column3ddiv'
                    //html: 'column3ddiv'
                },{
                    title: 'Another Panel 3',
                    tools: tools,
                    html:''
                }]
            }]
            
            /*
             * Uncomment this block to test handling of the drop event. You could use this
             * to save portlet position state for example. The event arg e is the custom 
             * event defined in Ext.ux.Portal.DropZone.
             */
//            ,listeners: {
//                'drop': function(e){
//                    Ext.Msg.alert('Portlet Dropped', e.panel.title + '<br />Column: ' + 
//                        e.columnIndex + '<br />Position: ' + e.position);
//                }
//            }
} /*{
            id:'welcome-panel',
            title: 'API Home',
            //autoLoad: {url: 'welcome.html', callback: this.initSearch, scope: this},
            iconCls:'icon-docs',
            autoScroll: true,
			      tbar: [
									'Search: ', ' ',
					                new Ext.ux.SelectBox({
					                    listClass:'x-combo-list-small',
					                    width:90,
					                    value:'Starts with',
					                    id:'search-type',
					                    store: new Ext.data.SimpleStore({
					                        fields: ['text'],
					                        expandData: true,
					                        data : ['Starts with', 'Ends with', 'Any match']
					                    }),
					                    displayField: 'text'
					                }), ' ',
					                new Ext.app.SearchField({
						                width:240,
										store: this.searchStore,
										paramName: 'q'
						            })
            ]
        }*/
    });
};

Ext.extend(MainPanel, Ext.TabPanel, {

    initEvents : function(){
        MainPanel.superclass.initEvents.call(this);
        this.body.on('click', this.onClick, this);
    },

    onClick: function(e, target){
        if(target = e.getTarget('a:not(.exi)', 3)){
            var cls = Ext.fly(target).getAttributeNS('ext', 'cls');
            e.stopEvent();
            if(cls){
                var member = Ext.fly(target).getAttributeNS('ext', 'member');
                this.loadClass(target.href, cls, member);
            }else if(target.className == 'inner-link'){
                this.getActiveTab().scrollToSection(target.href.split('#')[1]);
            }else{
                window.open(target.href);
            }
        }else if(target = e.getTarget('.micon', 2)){
            e.stopEvent();
            var tr = Ext.fly(target.parentNode);
            if(tr.hasClass('expandable')){
                tr.toggleClass('expanded');
            }
        }
    },

    loadClass : function(href, cls, member){
        var id = 'docs-' + cls;
        var tab = this.getComponent(id);
        if(tab){
            this.setActiveTab(tab);
            if(member){
                tab.scrollToMember(member);
            }
        }else{
            var autoLoad = {url: href,scripts:true};
            if(member){
                autoLoad.callback = function(){
                    Ext.getCmp(id).scrollToMember(member);
                }
            }
            var p = this.add(new DocPanel({
                id: id,
                cclass : cls,
                autoLoad: autoLoad,
                iconCls: Docs.icons[cls]
            }));
            this.setActiveTab(p);
        }
    },
    /**/
   addNewTab : function(panelobj, cls){
    	var id = 'docs-' + cls;
        var tab = this.getComponent(id);
        
        if(tab){
        	//alert('jia1');
        	this.setActiveTab(tab);
            if(member){
                tab.scrollToMember(member);
            }
        }else{
        	//alert('jia');
        	var p = this.add(panelobj );
        	
        	this.setActiveTab(p);
        }
    	
   },
	
	initSearch : function(){
		// Custom rendering Template for the View
	    var resultTpl = new Ext.XTemplate(
	        '<tpl for=".">',
	        '<div class="search-item">',
	            '<a class="member" ext:cls="{cls}" ext:member="{member}" href="output/{cls}.html">',
				'<img src="../resources/images/default/s.gif" class="item-icon icon-{type}"/>{member}',
				'</a> ',
				'<a class="cls" ext:cls="{cls}" href="output/{cls}.html">{cls}</a>',
	            '<p>{doc}</p>',
	        '</div></tpl>'
	    );
		
		var p = new Ext.DataView({
            applyTo: 'search',
			tpl: resultTpl,
			loadingText:'Searching...',
            store: this.searchStore,
            itemSelector: 'div.search-item',
			emptyText: '<h3>Use the search field above to search the Ext API for classes, properties, config options, methods and events.</h3>'
        });
	},
	
	doSearch : function(e){
		var k = e.getKey();
		if(!e.isSpecialKey()){
			var text = e.target.value;
			if(!text){
				this.searchStore.baseParams.q = '';
				this.searchStore.removeAll();
			}else{
				this.searchStore.baseParams.q = text;
				this.searchStore.reload();
			}
		}
	}
});


SouthPanel = function (){
	this.__ctxPath ='resources/js';
	MainPanel.superclass.constructor.call(this, {
        region:'south',
        height:0,
        tbar:[{text:"退出系统",iconCls:"btn-logout",handler:function(){App.Logout();}},"-",
	  	      {text:"在线用户",iconCls:"btn-onlineUser",handler:function(){OnlineUserSelector.getView().show();}},"-",
	  	      {text:"意见箱",iconCls:"btn-suggest-box",handler:function(){App.clickTopTab("SuggestBoxView",{title:"我的意见箱",
	  	      	userId:curUserInfo.userId});}},"-",
	  	      {id:"messageTip",xtype:"button",hidden:true,width:50,height:20,handler:function(){var a=Ext.getCmp("messageTip");
	  	      	var b=Ext.getCmp("win");if(b==null){new MessageWin().show();}a.hide();}},"->",
	  	      {xtype:"tbfill"},
	  	      {xtype:"tbtext",text:"协作内容管理平台",id:"toolbarCompanyName"},
	  	      {xtype:"tbseparator"},
	  	      new Ext.Toolbar.TextItem('技术支持 <a href=http://www.bplow.com target="_blank">上海陆鹰实业有限公司</a>'),
	  	      {xtype:"tbseparator"},
	  	      {pressed:false,text:"便签",iconCls:"tipsTile",handler:function(){App.clickTopTab("PersonalTipsView");}},
	  	      {xtype:"tbseparator"},
	  	      {pressed:false,text:"与我们联系",handler:function(){Ext.ux.Toast.msg("联系我们","电话：15890625022<br/>网址：http://www.bplow.com");}},"-",
	  	      {text:"收展",iconCls:"btn-expand",handler:function(){var a=Ext.getCmp("__nortPanel");if(a.collapsed){a.expand(true);}else{a.collapse(true);}}},"-",
	  	      {xtype:"combo",mode:"local",editable:false,value:"切换皮肤",width:100,triggerAction:"all",store:[["ext-all","缺省浅蓝"],["ext-all-notheme","绿色主题"],["ext-all-css03","粉红主题"],["xtheme-gray","灰色主题"],["xtheme-default2","灰蓝主题"],["xtheme-default16","绿色主题"],["xtheme-access","Access风格"]],listeners:{scope:this,"select":function(d,b,c){if(d.value!=""){var a=new Date();a.setDate(a.getDate()+300);/*setCookie("theme",d.value,a,this.__ctxPath);*/Ext.util.CSS.swapStyleSheet("theme",this.__ctxPath+"/ext-3.4.0/resources/css/"+d.value+".css");}}}}]
	});
}
Ext.extend(SouthPanel, Ext.Panel, {});

Ext.onReady(function(){

    Ext.QuickTips.init();

    var api = new ApiPanel();
    var mainPanel = new MainPanel();
    
    Ext.mainScreem = mainPanel;

    api.on('click', function(node, e){
         //alert(node.iconCls);
         /*if(node.isLeaf()){
            e.stopEvent();
            mainPanel.loadClass(node.attributes.href, node.id);
         }*/
         /**/
         if(node.isLeaf() && node.attributes.frameType == undefined ){
		    		e.stopEvent();
		    		mainPanel.loadClass(node.attributes.href,node.id,null);
    	   }else{
    		    e.stopEvent();
    		    mainPanel.addNewTab(returnmif({id:node.id,title:node.text,iconCls:node.attributes.iconCls,src:node.attributes.href}),node.id);
    	   }
         
         
         
         
         
    });

    mainPanel.on('tabchange', function(tp, tab){
        api.selectClass(tab.cclass); 
    });

    var viewport = new Ext.Viewport({
        layout:'border',
        items:[ {
            cls: 'docs-header',
            height: 44,
            region:'north',
            xtype:'box',
            el:'header',
            border:false,
            margins: '0 0 0 0'
        }, api, mainPanel,new SouthPanel() ]
    });

    api.expandPath('/root/apidocs');

    // allow for link in
    /*
    var page = window.location.href.split('?')[1];
    if(page){
        var ps = Ext.urlDecode(page);
        var cls = ps['class'];
        mainPanel.loadClass('output/' + cls + '.html', cls, ps.member);
    }*/
    
    viewport.doLayout();
    
    
    var chart = new FusionCharts("resources/Charts/Pie3D.swf", "ChartId", "350", "400", "0", "0");
		chart.setXMLUrl("resources/data/xml/Pie3D2.xml");		   
		chart.render("chartdiv");
    
	var chart2 = new FusionCharts("resources/Charts/Column3D.swf", "ChartId", "350", "400", "0", "0");
	chart2.setXMLUrl("resources/data/xml/Col3D1.xml");		   
	chart2.render("column3ddiv");
	
	var chart3 = new FusionCharts("resources/Charts/MSColumn3DLineDY.swf", "ChartId", "350", "400", "0", "0");
	chart3.setXMLUrl("resources/data/xml/Col3DLineDY1.xml");		   
	chart3.render("column4ddiv");
	
	setTimeout(function(){
        Ext.get('loading').remove();
        Ext.get('loading-mask').fadeOut({remove:true});
    }, 250);
	
});


Ext.app.SearchField = Ext.extend(Ext.form.TwinTriggerField, {
    initComponent : function(){
        if(!this.store.baseParams){
			this.store.baseParams = {};
		}
		Ext.app.SearchField.superclass.initComponent.call(this);
		this.on('specialkey', function(f, e){
            if(e.getKey() == e.ENTER){
                this.onTrigger2Click();
            }
        }, this);
    },

    validationEvent:false,
    validateOnBlur:false,
    trigger1Class:'x-form-clear-trigger',
    trigger2Class:'x-form-search-trigger',
    hideTrigger1:true,
    width:180,
    hasSearch : false,
    paramName : 'query',

    onTrigger1Click : function(){
        if(this.hasSearch){
            this.store.baseParams[this.paramName] = '';
			this.store.removeAll();
			this.el.dom.value = '';
            this.triggers[0].hide();
            this.hasSearch = false;
			this.focus();
        }
    },

    onTrigger2Click : function(){
        var v = this.getRawValue();
        if(v.length < 1){
            this.onTrigger1Click();
            return;
        }
		if(v.length < 2){
			Ext.Msg.alert('Invalid Search', 'You must enter a minimum of 2 characters to search the API');
			return;
		}
		this.store.baseParams[this.paramName] = v;
        var o = {start: 0};
        this.store.reload({params:o});
        this.hasSearch = true;
        this.triggers[0].show();
		this.focus();
    }
});


/**
 * Makes a ComboBox more closely mimic an HTML SELECT.  Supports clicking and dragging
 * through the list, with item selection occurring when the mouse button is released.
 * When used will automatically set {@link #editable} to false and call {@link Ext.Element#unselectable}
 * on inner elements.  Re-enabling editable after calling this will NOT work.
 *
 * @author Corey Gilmore
 * http://extjs.com/forum/showthread.php?t=6392
 *
 * @history 2007-07-08 jvs
 * Slight mods for Ext 2.0
 */
Ext.ux.SelectBox = function(config){
	this.searchResetDelay = 1000;
	config = config || {};
	config = Ext.apply(config || {}, {
		editable: false,
		forceSelection: true,
		rowHeight: false,
		lastSearchTerm: false,
        triggerAction: 'all',
        mode: 'local'
    });

	Ext.ux.SelectBox.superclass.constructor.apply(this, arguments);

	this.lastSelectedIndex = this.selectedIndex || 0;
};

Ext.extend(Ext.ux.SelectBox, Ext.form.ComboBox, {
    lazyInit: false,
	initEvents : function(){
		Ext.ux.SelectBox.superclass.initEvents.apply(this, arguments);
		// you need to use keypress to capture upper/lower case and shift+key, but it doesn't work in IE
		this.el.on('keydown', this.keySearch, this, true);
		this.cshTask = new Ext.util.DelayedTask(this.clearSearchHistory, this);
	},

	keySearch : function(e, target, options) {
		var raw = e.getKey();
		var key = String.fromCharCode(raw);
		var startIndex = 0;

		if( !this.store.getCount() ) {
			return;
		}

		switch(raw) {
			case Ext.EventObject.HOME:
				e.stopEvent();
				this.selectFirst();
				return;

			case Ext.EventObject.END:
				e.stopEvent();
				this.selectLast();
				return;

			case Ext.EventObject.PAGEDOWN:
				this.selectNextPage();
				e.stopEvent();
				return;

			case Ext.EventObject.PAGEUP:
				this.selectPrevPage();
				e.stopEvent();
				return;
		}

		// skip special keys other than the shift key
		if( (e.hasModifier() && !e.shiftKey) || e.isNavKeyPress() || e.isSpecialKey() ) {
			return;
		}
		if( this.lastSearchTerm == key ) {
			startIndex = this.lastSelectedIndex;
		}
		this.search(this.displayField, key, startIndex);
		this.cshTask.delay(this.searchResetDelay);
	},

	onRender : function(ct, position) {
		this.store.on('load', this.calcRowsPerPage, this);
		Ext.ux.SelectBox.superclass.onRender.apply(this, arguments);
		if( this.mode == 'local' ) {
			this.calcRowsPerPage();
		}
	},

	onSelect : function(record, index, skipCollapse){
		if(this.fireEvent('beforeselect', this, record, index) !== false){
			this.setValue(record.data[this.valueField || this.displayField]);
			if( !skipCollapse ) {
				this.collapse();
			}
			this.lastSelectedIndex = index + 1;
			this.fireEvent('select', this, record, index);
		}
	},

	render : function(ct) {
		Ext.ux.SelectBox.superclass.render.apply(this, arguments);
		if( Ext.isSafari ) {
			this.el.swallowEvent('mousedown', true);
		}
		this.el.unselectable();
		this.innerList.unselectable();
		this.trigger.unselectable();
		this.innerList.on('mouseup', function(e, target, options) {
			if( target.id && target.id == this.innerList.id ) {
				return;
			}
			this.onViewClick();
		}, this);

		this.innerList.on('mouseover', function(e, target, options) {
			if( target.id && target.id == this.innerList.id ) {
				return;
			}
			this.lastSelectedIndex = this.view.getSelectedIndexes()[0] + 1;
			this.cshTask.delay(this.searchResetDelay);
		}, this);

		this.trigger.un('click', this.onTriggerClick, this);
		this.trigger.on('mousedown', function(e, target, options) {
			e.preventDefault();
			this.onTriggerClick();
		}, this);

		this.on('collapse', function(e, target, options) {
			Ext.getDoc().un('mouseup', this.collapseIf, this);
		}, this, true);

		this.on('expand', function(e, target, options) {
			Ext.getDoc().on('mouseup', this.collapseIf, this);
		}, this, true);
	},

	clearSearchHistory : function() {
		this.lastSelectedIndex = 0;
		this.lastSearchTerm = false;
	},

	selectFirst : function() {
		this.focusAndSelect(this.store.data.first());
	},

	selectLast : function() {
		this.focusAndSelect(this.store.data.last());
	},

	selectPrevPage : function() {
		if( !this.rowHeight ) {
			return;
		}
		var index = Math.max(this.selectedIndex-this.rowsPerPage, 0);
		this.focusAndSelect(this.store.getAt(index));
	},

	selectNextPage : function() {
		if( !this.rowHeight ) {
			return;
		}
		var index = Math.min(this.selectedIndex+this.rowsPerPage, this.store.getCount() - 1);
		this.focusAndSelect(this.store.getAt(index));
	},

	search : function(field, value, startIndex) {
		field = field || this.displayField;
		this.lastSearchTerm = value;
		var index = this.store.find.apply(this.store, arguments);
		if( index !== -1 ) {
			this.focusAndSelect(index);
		}
	},

	focusAndSelect : function(record) {
		var index = typeof record === 'number' ? record : this.store.indexOf(record);
		this.select(index, this.isExpanded());
		this.onSelect(this.store.getAt(record), index, this.isExpanded());
	},

	calcRowsPerPage : function() {
		if( this.store.getCount() ) {
			this.rowHeight = Ext.fly(this.view.getNode(0)).getHeight();
			this.rowsPerPage = this.maxHeight / this.rowHeight;
		} else {
			this.rowHeight = false;
		}
	}

});

Ext.Ajax.on('requestcomplete', function(ajax, xhr, o){
    if(typeof urchinTracker == 'function' && o && o.url){
        urchinTracker(o.url);
    }
});

/**/
function returnmif(obj){
	
	MiframeWin = Ext.extend(Ext.ux.ManagedIFrame.Component,
  		  {
  		  frameConfig : {
  		                name : 'framePreview',
  		                frameborder : 0,
  		                allowtransparency : true
  		  }
  		  }
   );
	 
	 
  var mycntiframe = new MiframeWin({
  id    : obj.id,
  title : obj.title,
  tabTip : obj.title,
  closable : true,
  iconCls:obj.iconCls,//页签应用样式
  loadMask  : true,
  frame : true,
  defaultSrc : obj.src,
  layout : 'fit',
  margins : '0 0 0 0'
         
  });
  
  return mycntiframe;
}
Ext.getmifObj =returnmif;
