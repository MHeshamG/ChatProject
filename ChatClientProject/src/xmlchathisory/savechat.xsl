<?xml version="1.0" encoding="UTF-8"?>


<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

   
    
     <xsl:variable name="sender" select="/history/@sender"/>
    <xsl:template match="/history">
        <html>
            <head>
                <title style="color:white"><h1><xsl:value-of select="$sender"/></h1></title>
            </head>
            <style type="text/css">
                    @import url(http://fonts.googleapis.com/css?family=Lato:100,300,400,700);
                    @import url(http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css);
                    html,
                    body {
                    background:	#800080;
                    font-family: 'Lato', sans-serif;
                    margin-left:20px;
                    margin-right:20px;
                    
                    
                    }

                
                    ::selection {
                   background: rgba(82, 179, 217, 0.3); 
                    color: inherit;
                    }

                
                    a {
                    color: rgba(82, 179, 217, 0.9);
                    }
                    
                   / MUNU/
                    .menu {
                    position: fixed;
                    top: 0px;
                    left: 0px;
                    right: 0px;
                    width: 70%;
                    height: 50px;
                    background: rgba(82, 179, 217, 0.9);
                    z-index: 100;
                    -webkit-touch-callout: none;
                    -webkit-user-select: none;
                    -moz-user-select: none;
                    -ms-user-select: none;
                    }

                  

               

                    .back:active {
                    background: rgba(255, 255, 255, 0.2);
                    }

               

                    .name {
                    position: absolute;
                    top: 3px;
                    left: 110px;
                    font-family: 'Lato';
                    font-size: 40px;
                    font-weight: 300;
                    color: white;
                    cursor: default;
                    }

                   
             

                    .chat {
                    list-style: none;
                    background: none;
                    margin: 0;
                    padding: 0 0 50px 0;
                    margin-top: 60px;
                    margin-bottom: 10px;
                    }

                    .chat li {
                    padding: 0.5rem;
                    overflow: hidden;
                    display: flex;

                    }

                    .chat .avatar {
                    width: 40px;
                    height: 20px;
                    position: relative;
                    display: block;
                    z-index: 2;
                    border-radius: 20%;
                    -webkit-border-radius: 20%;
                    -moz-border-radius: 20%;
                    -ms-border-radius: 20%;
                    background-color: rgba(255, 255, 255, 0.9);
                    }

                

                  
                    .other .msg {
                    order: 1;
                    border-top-left-radius: 0px;
                    box-shadow: -1px 2px 0px #D4D4D4;
                    }

                    .other:before {
                    content: "";
                    position: relative;
                    top: 0px;
                    right: 0px;
                    left: 40px;
                    width: 0px;
                    height: 0px;
                    border: 5px solid #fff;
                    border-left-color: transparent;
                    border-bottom-color: transparent;
                    }

                    .self {
                    justify-content: flex-end;
                    align-items: flex-end;
               
                        
                    }

                    .self .msg {
                    order: 1;
                    border-bottom-right-radius: 0px;
                    box-shadow: 1px 2px 0px #D4D4D4;
                    }

                    .self .avatar {
                    order: 2;
                    }

                    .self .avatar:after {
                    content: "";
                    position:relative;
                    display: inline-block;
                    bottom: 19px;
                   right:0px;
                    width: 0px;
                    height: 0px;
                    border: 5px solid #fff;
                    border-right-color: transparent;
                    border-top-color: transparent;
                    box-shadow: 0px 2px 0px #D4D4D4;
                    }

                    .msg {
                    background: white;
                    min-width: 50px;
                    padding: 10px;
                    border-radius: 2px;
                    box-shadow: 0px 2px 0px rgba(0, 0, 0, 0.07);
                    }

                    .msg p {
                    font-size: 0.8rem;
                    margin: 0 0 0.2rem 0;
                    color: #777;
                    }

                  

                    @media screen and (max-width: 800px) {
                    .msg img {
                    width: 300px;
                    }
                    }

                    @media screen and (max-width: 550px) {
                    .msg img {
                    width: 200px;
                    }
                    }

               

                    @-webikt-keyframes pulse {
                    from {
                    opacity: 0;
                    }
                    to {
                    opacity: 0.5;
                    }
                    }

                    ::-webkit-scrollbar {
                    min-width: 12px;
                    width: 12px;
                    max-width: 12px;
                    min-height: 12px;
                    height: 12px;
                    max-height: 12px;
                    background: #e5e5e5;
                    box-shadow: inset 0px 50px 0px rgba(82, 179, 217, 0.9), inset 0px -52px 0px #fafafa;
                    }

                    ::-webkit-scrollbar-thumb {
                    background: #bbb;
                    border: none;
                    border-radius: 100px;
                    border: solid 3px #e5e5e5;
                    box-shadow: inset 0px 0px 3px #999;
                    }

                    ::-webkit-scrollbar-thumb:hover {
                    background: #b0b0b0;
                    box-shadow: inset 0px 0px 3px #888;
                    }

                    ::-webkit-scrollbar-thumb:active {
                    background: #aaa;
                    box-shadow: inset 0px 0px 3px #7f7f7f;
                    }

                    ::-webkit-scrollbar-button {
                    display: block;
                    height: 26px;
                    }
                
               
               
                </style>
            
            <body>
                <div class="menu">
                    
                    <div class="name"><xsl:value-of select="message/to"/></div>
                   
                          <ol class="chat">
                    
                    <xsl:for-each select="message">
                        <xsl:choose>
                            <xsl:when test="from = $sender">
                                <li class="self" >
                                    <div class="avatar" >
                                        <p style="color:white; float:right;"><xsl:value-of select="$sender"/></p>
                                    </div>
                                    <div class="msg" >
                                        <p style="
                                    color:#800080;
                                    font-size: 30px;
                                    font-family:'Lato', sans-serif ;
                                    font-weight: strong;
                                    float:right;
                                    ">
                                            <xsl:value-of select="body"/>
                                        </p>
                                      
                                    </div>
                                </li>
                            </xsl:when>
                            <xsl:otherwise>
                                <li class="other">
                                    <div class="avatar">
                                        <p> <xsl:value-of select="message/to"/> </p>
                                    </div>
                                    <div class="msg">
                                      
                                       <p style="
                                    color:#800080 ;
                                    font-size: 25px;
                                    font-family:'Lato', sans-serif ;
                                    font-weight: strong;
                                    ">
                                            <xsl:value-of select="body"/>
                                        </p>
                                     
                                    </div>
                                </li>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:for-each> 
                </ol>
                </div>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>

