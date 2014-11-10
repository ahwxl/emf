myportal ={
            xtype:'portal',
            region:'center',
            margins:'35 5 5 0',
            items:[{
                columnWidth:.33,
                style:'padding:10px 0 10px 10px',
                items:[{
                    title: 'Grid in a Portlet',
                    layout:'fit',
                    tools: tools,
                    html: 'new SampleGrid([0, 2, 3])'
                },{
                    title: 'Another Panel 11',
                    tools: tools,
                    html: ''
                }]
            },{
                columnWidth:.33,
                style:'padding:10px 0 10px 10px',
                items:[{
                    title: 'Panel 2',
                    tools: tools,
                    html: ''
                },{
                    title: 'Another Panel 2',
                    tools: tools,
                    html: ''
                }]
            },{
                columnWidth:.33,
                style:'padding:10px',
                items:[{
                    title: 'Panel 3',
                    tools: tools,
                    html: ''
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
};