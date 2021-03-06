/*
 * Copyright © 2017 Logistimo.
 *
 * This file is part of Logistimo.
 *
 * Logistimo software is a mobile & web platform for supply chain management and remote temperature monitoring in
 * low-resource settings, made available under the terms of the GNU Affero General Public License (AGPL).
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along with this program.  If not, see
 * <http://www.gnu.org/licenses/>.
 *
 * You can be released from the requirements of the license by purchasing a commercial license. To know more about
 * the commercial license, please contact us at opensource@logistimo.com
 */

var fs=require("fs"),url=require("url"),path=require("path"),util=require("util");exports.ping={name:"ping",init:function(){},exec:function(n,t){var e=this,f=url.parse(n.url,!0),u=f.query,r;u.ping!==undefined&&(r='<?xml version="1.0" encoding="utf-8"?><server><status>OK</status></server>',t.writeHead(200,{"Content-Type":"text/xml"}),t.end(r),console.log("Server status: OK"))}},exports.search={name:"search",options:{searchData:"./../../searchindex.js",root:"",engine:"./nethelp.search.engine.js",disableJSONP:!1},init:function(n){var u=this,e=u.options,f,o,r,t,s,i;for(e.disableJSONP=n.disableJSONP,u._root=n.path,f=u._targets=n.targets||[{path:"",index:n.searchindex||e.searchData}],o=require(e.engine),r=0;r<f.length;r++)t=f[r],s=path.existsSync(t.index)?t.index:path.join(__dirname,t.index),t.engine=new o.engine,i=fs.readFileSync(s,"utf8"),i.lastIndexOf("\ufeff",0)===0&&(i=i.substring(1,i.length)),t.data=JSON.parse(i.replace(/^.*<!-- saved from url=[^>]+-->\n?/i,"")),t.engine.init(t.data),t.path=t.path.toLowerCase(),t.path.charAt(0)!=="/"&&(t.path="/"+t.path),t.path.charAt(t.path.length-1)!=="/"&&(t.path+="/")},exec:function(n,t){var h=this,a=h.options,c=url.parse(n.url,!0),l=c.pathname.toLowerCase(),e=c.query,u=h._targets,f,r,o,s;if(e.search){for(r=0;r<u.length;r++)if(u[r].path==l){f=u[r];break}else u[r].path==path.dirname(l)+"/"&&(f=u[r]);f||u.length!==1||(f=u[0]),f&&(o=f.engine.start(e.search),s=!a.disableJSONP&&e.callback?util.format("%s (%j)",e.callback,o):util.format("%j",o),t.writeHead(200,{"Content-Type":"application/json"}),t.end(s))}}}