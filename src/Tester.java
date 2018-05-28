public class Tester {
    public static void main(String args[]){
        int num =1;

        //constructors safety test
        //-----------------------------------------------------------------------
        do {
            try {
                BTree test = new BTree("-1");
                System.out.println("failed test " + num + " t cant be negative");
                num++;
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("success test " + num);
                num++;
            }
            try {
                BTree test = new BTree("0");
                System.out.println("failed test " + num + " t cant be 0");
                num++;
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("success test " + num);
                num++;
            }
            try {
                HashTable test = new HashTable("-1");
                System.out.println("failed test " + num + " m cant be negative");
                num++;
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("success test " + num);
                num++;
            }

            try {
                HashTable test = new HashTable("0");
                System.out.println("failed test " + num + " m cant be 0");
                num++;
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("success test " + num);
                num++;
            }

            //-----------------------------------------------------------------------
            //BTree function Safety test
            BTree testBTree = new BTree("5");
            try {
                testBTree.insert(null);
                System.out.println("failed test "+num+" can't insert null");
                break;
            } catch (NullPointerException e) {
                System.out.println("success test " + num);
                num++;
            }
            try {
                testBTree.insert("");
                System.out.println("failed test "+num+" can't insert empty String");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("success test " + num);
                num++;
            }
            try {
                testBTree.search(null);
                System.out.println("failed test "+num+" can't search null");
                break;
            } catch (NullPointerException e) {
                System.out.println("success test " + num);
                num++;
            }
            try {
                testBTree.search("");
                System.out.println("failed test "+num+" can't search empty String");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("success test " + num);
                num++;
            }
            //-----------------------------------------------------------------------
            //BTree building test:
            for(char ch='a';ch<='z';ch++){
                for(char cha='a';cha<='z';cha++){
                        testBTree.insert(""+ch+cha);
                }
            }
            String s = testBTree.toString();
            if(s.equals("eu,jp,ok,tf#ay,bx,cw,dv|ft,gs,hr,iq|ko,ln,mm,nl|pj,qi,rh,sg|ue,vd,wc,xb,ya,yz#ae,aj,ao,at|bd,bi,bn,bs|cc,ch,cm,cr|db,dg,dl,dq|ea,ef,ek,ep^ez,fe,fj,fo|fy,gd,gi,gn|gx,hc,hh,hm|hw,ib,ig,il|iv,ja,jf,jk^ju,jz,ke,kj|kt,ky,ld,li|ls,lx,mc,mh|mr,mw,nb,ng|nq,nv,oa,of^op,ou,oz,pe|po,pt,py,qd|qn,qs,qx,rc|rm,rr,rw,sb|sl,sq,sv,ta^tk,tp,tu,tz|uj,uo,ut,uy|vi,vn,vs,vx|wh,wm,wr,ww|xg,xl,xq,xv|yf,yk,yp,yu|ze,zj,zo,zt#aa,ab,ac,ad|af,ag,ah,ai|ak,al,am,an|ap,aq,ar,as|au,av,aw,ax^az,ba,bb,bc|be,bf,bg,bh|bj,bk,bl,bm|bo,bp,bq,br|bt,bu,bv,bw^by,bz,ca,cb|cd,ce,cf,cg|ci,cj,ck,cl|cn,co,cp,cq|cs,ct,cu,cv^cx,cy,cz,da|dc,dd,de,df|dh,di,dj,dk|dm,dn,do,dp|dr,ds,dt,du^dw,dx,dy,dz|eb,ec,ed,ee|eg,eh,ei,ej|el,em,en,eo|eq,er,es,et^ev,ew,ex,ey|fa,fb,fc,fd|ff,fg,fh,fi|fk,fl,fm,fn|fp,fq,fr,fs^fu,fv,fw,fx|fz,ga,gb,gc|ge,gf,gg,gh|gj,gk,gl,gm|go,gp,gq,gr^gt,gu,gv,gw|gy,gz,ha,hb|hd,he,hf,hg|hi,hj,hk,hl|hn,ho,hp,hq^hs,ht,hu,hv|hx,hy,hz,ia|ic,id,ie,if|ih,ii,ij,ik|im,in,io,ip^ir,is,it,iu|iw,ix,iy,iz|jb,jc,jd,je|jg,jh,ji,jj|jl,jm,jn,jo^jq,jr,js,jt|jv,jw,jx,jy|ka,kb,kc,kd|kf,kg,kh,ki|kk,kl,km,kn^kp,kq,kr,ks|ku,kv,kw,kx|kz,la,lb,lc|le,lf,lg,lh|lj,lk,ll,lm^lo,lp,lq,lr|lt,lu,lv,lw|ly,lz,ma,mb|md,me,mf,mg|mi,mj,mk,ml^mn,mo,mp,mq|ms,mt,mu,mv|mx,my,mz,na|nc,nd,ne,nf|nh,ni,nj,nk^nm,nn,no,np|nr,ns,nt,nu|nw,nx,ny,nz|ob,oc,od,oe|og,oh,oi,oj^ol,om,on,oo|oq,or,os,ot|ov,ow,ox,oy|pa,pb,pc,pd|pf,pg,ph,pi^pk,pl,pm,pn|pp,pq,pr,ps|pu,pv,pw,px|pz,qa,qb,qc|qe,qf,qg,qh^qj,qk,ql,qm|qo,qp,qq,qr|qt,qu,qv,qw|qy,qz,ra,rb|rd,re,rf,rg^ri,rj,rk,rl|rn,ro,rp,rq|rs,rt,ru,rv|rx,ry,rz,sa|sc,sd,se,sf^sh,si,sj,sk|sm,sn,so,sp|sr,ss,st,su|sw,sx,sy,sz|tb,tc,td,te^tg,th,ti,tj|tl,tm,tn,to|tq,tr,ts,tt|tv,tw,tx,ty|ua,ub,uc,ud^uf,ug,uh,ui|uk,ul,um,un|up,uq,ur,us|uu,uv,uw,ux|uz,va,vb,vc^ve,vf,vg,vh|vj,vk,vl,vm|vo,vp,vq,vr|vt,vu,vv,vw|vy,vz,wa,wb^wd,we,wf,wg|wi,wj,wk,wl|wn,wo,wp,wq|ws,wt,wu,wv|wx,wy,wz,xa^xc,xd,xe,xf|xh,xi,xj,xk|xm,xn,xo,xp|xr,xs,xt,xu|xw,xx,xy,xz^yb,yc,yd,ye|yg,yh,yi,yj|yl,ym,yn,yo|yq,yr,ys,yt|yv,yw,yx,yy^za,zb,zc,zd|zf,zg,zh,zi|zk,zl,zm,zn|zp,zq,zr,zs|zu,zv,zw,zx,zy,zz")){
                System.out.println("success test " + num);
                num++;
            }
            else{
                System.out.println("failed test "+num+" 2 options:\n1. BFS not working well.(try using a queue)\n2. tree not working well (try praying to a different god)");
                break;
            }
            //-----------------------------------------------------------------------
            //BTree Search test:
            boolean flag =true;
            for(char ch='a';flag&&ch<='z';ch++){
                for(char cha='a';flag&&cha<='z';cha++){
                    if(testBTree.search(""+ch+cha)==null){           //assuming your search function returning null if it can't find the item, remove "== null" if its boolean function
                        System.out.println("failed test "+num+" didn't find what you were looking for\nyou were looking for: "+ch+cha);
                        flag =false;
                    }
                }
            }
            if(flag){
                System.out.println("success test " + num);
                num++;
            }
            else{
                break;
            }
            for(char ch='a';flag&&ch<='z';ch++){
                for(char c='a';flag&&c<='z';c++){
                    if(testBTree.search(""+ch+c+" ")!=null){          //assuming your search function returning null if it can't find the item, remove "!= null" and add "!" if its boolean function
                        System.out.println("failed test "+num+" find a key that doesn't exist\ni added a space to an existing key at the end");
                        flag =false;
                    }
                    if(testBTree.search(" "+ch+c)!=null){            //assuming your search function returning null if it can't find the item, remove "!= null" and add "!" if its boolean function
                        System.out.println("failed test "+num+" find a key that doesn't exist\ni added a space to an existing key at the start");
                        flag =false;
                    }
                }
            }
            if(flag){
                System.out.println("success test " + num);
                num++;
            }
            else{
                break;
            }
            //-----------------------------------------------------------------------
            //runTime test
            //i can only test runtime for insert (log(n)) if some one knows how to test o(1) ill be happy to hear that
            //this loop only make a very big tree
            long starTime =System.currentTimeMillis();
            for(char ch='a';ch<='z';ch++){
                for(char c='a';c<='z';c++){
                    for(char cha='a';cha<='z';cha++) {
                        for (char chaR = 'a'; chaR <= 'z'; chaR++) {
                            for(char x = 'a';x<='z';x++) {
                                testBTree.insert("" + ch + c + ch + chaR+x);
                            }
                        }
                    }
                }
            }
            long endTime = System.currentTimeMillis();
            long duration = (endTime-starTime);
            System.out.println("test "+num+":\nEnter all those keys took "+ duration+" milliseconds\nit took me around 5000 milliseconds\nif your number is around that area its good.");
            //-----------------------------------------------------------------------
            //HashTable safety check
            HashTable testHashTable = new HashTable("500");
            try{
                testHashTable.insert(null);
                System.out.println("failed test "+num+" can't insert null");
                break;
            }
            catch (NullPointerException e){
                System.out.println("success test " + num);
                num++;
            }
            try{
                testHashTable.insert("");
                System.out.println("failed test "+num+" can't insert empty String");
                break;
            }
            catch (IllegalArgumentException e){
                System.out.println("success test " + num);
                num++;
            }
            //-----------------------------------------------------------------------
            //hash table building test:
            starTime =System.currentTimeMillis();
            for(char ch='a';ch<='z';ch++){
                for(char c='a';c<='z';c++){
                    for(char cha='a';cha<='z';cha++) {
                        for (char chaR = 'a'; chaR <= 'z'; chaR++) {
                                testHashTable.insert("" + ch + c + ch + chaR);
                        }
                    }
                }
            }
            endTime = System.currentTimeMillis();
            duration = (endTime-starTime);
            System.out.println("test "+num+":\nEnter all those keys took "+ duration+" milliseconds\nit took me around 20 milliseconds\nif your number is around that area its good.");
        }while (false);
    }
}
