/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate.tic.tac.toe;

// // Copyright 2016 theaigames.com (developers@theaigames.com)

//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at

//        http://www.apache.org/licenses/LICENSE-2.0

//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//
//    For the full copyright and license information, please view the LICENSE
//    file that was distributed with this source code.
import java.util.ArrayList;
import java.util.Random;

/**
 * BotStarter class
 * 
 * Magic happens here. You should edit this file, or more specifically
 * the makeTurn() method to make your bot do more than random moves.
 * 
 * @author Jim van Eeden <jim@starapple.nl>
 */

public class BotStarter {

    /**
     * Makes a turn. Edit this method to make your bot smarter.
     * Currently does only random moves.
     *
     * @return The column where the turn was made.
     */
    
    
    public int dominance( Field f, Bounds b) {
        int[][] moves = f.getAvailableMoves();    
        int i,j, count1 = 0, count2 = 0;
      
        for(i = b.x_min; i < b.x_max; i++) 
            for(j = b.y_min; j < b.y_max; j++) {
                if(moves[i][j] == 1)
                    count1++;
                if(moves[i][j] == 2)
                    count2++;
            } 
        
        return count1 - count2;
    }
    
    boolean can_we_close(Field f, Bounds b) {
        int[][] moves = f.getAvailableMoves();
        int i,j;
        int m1,m2,m3,m11,m22,m33,m13,m31;
        
        //verific daca pot inchide pe linii
        for(i = b.x_min; i < b.x_max; i++) {
            m1=moves[i][b.y_min]; // casuta stanga
            m2=moves[i][b.y_min+1]; //casuta mijloc
            m3=moves[i][b.y_max-1]; //casuta dreapta
            
            if((m1 == 1 && m2 == 1 && m3 == 0) || (m1 ==1 && m2 == 0 && m3 == 1) || (m1 == 0 && m2 == 1 && m3 == 1))
                return true;
        }
        //verific daca pot inchide pe coloane
        for(j = b.y_min; j < b.y_max; j++) {
            m1=moves[b.x_min][j];//casuta sus
            m2=moves[b.x_min+1][j];//casuta mijloc
            m3=moves[b.x_max-1][j];//casuta jos
            
            if((m1 == 1 && m2 == 1 && m3 == 0) || (m1 ==1 && m2 == 0 && m3 == 1) || (m1 == 0 && m2 == 1 && m3 == 1))
                return true;
        }
        //verific daca pot inchide pe diagonala principala
        m11=moves[b.x_min][b.y_min];
        m22=moves[b.x_min+1][b.y_min+1];
        m33=moves[b.x_max-1][b.y_max-1];
        if((m11 == 1 && m22 == 1 && m33 == 0) || (m11 == 1 && m22 == 0 && m33 == 1) || (m11 == 0 && m22 == 1 && m33  == 1))
            return true;
        //verific daca pot inchide pe diagonala secundara
        m13=moves[b.x_min][b.y_max-1];
        m31=moves[b.x_max-1][b.y_min];    
        if((m13 == 1 && m22 == 1 && m31 == 0) || (m13 == 1 && m22 == 0 && m31 == 1) || (m13 == 0 && m22 == 1 && m31  == 1))
            return true;
        
        return false;
    }
    public boolean can_enemy_close(Field field,Bounds b)
    {
        int[][] moves = field.getAvailableMoves();
        int m1,m2,m3,m11,m22,m33,m31,m13;
        for (int y = b.y_min; y < b.y_max; y++) 
        {
            m1=moves[b.x_min][y];//casuta sus
            m2=moves[b.x_min+1][y];//casuta mijloc
            m3=moves[b.x_max-1][y];//casuta jos
            if(m1==2 && m2==2)
                if(m3==0)
                    return true;
            if(m1==2 && m3==2)
                if(m2==0)
                    return true;
            if (m3==2 && m2==2)
                if(m1==0)
                    return true;
        }
        for (int x = b.x_min; x < b.x_max; x++) 
        {
            m1=moves[x][b.y_min]; // casuta stanga
            m2=moves[x][b.y_min+1]; //casuta mijloc
            m3=moves[x][b.y_max-1]; //casuta dreapta
            if(m1==2 && m2==2)
                if(m3==0)
                    return true;
            if(m1==2 && m3==2)
                if(m2==0)
                    return true;
            if (m3==2 && m2==2)
                if(m1==0)
                    return true;
        }
        m11=moves[b.x_min][b.y_min];
        m22=moves[b.x_min+1][b.y_min+1];
        m33=moves[b.x_max-1][b.y_max-1];
        m13=moves[b.x_min][b.y_max-1];
        m31=moves[b.x_max-1][b.y_min];
        if(m11==2 && m22==2)
            if(m33==0)
                return true;
        if(m11==2 && m33==2)
            if(m22==0)
                return true;
        if(m22==2 && m33==2)
            if(m11==0)
                return true;
        if(m31==2 && m13==2)
            if(m22==0)
                return true;
        if(m22==2 && m13==2)
            if(m31==0)
                return true;
        if(m31==2 && m22==2)
            if(m13==0)
                return true;
        return false;
    }
    public Bounds getMacroboardBounds(int x,int y)
    {
        if(x==0 || x==3 || x==6)
        {
            if(y==0 || y==3 || y==6)
                return new Bounds(0,3,0,3);
            if(y==1 || y==4 || y==7)
                return new Bounds(0,3,3,6);
            if(y==2 || y==5 || y==8)
                return new Bounds(0,3,6,9);
        }
        if(x==1 || x==4 || x==7)
        {
            if(y==0 || y==3 || y==6)
                return new Bounds(3,6,0,3);
            if(y==1 || y==4 || y==7)
                return new Bounds(3,6,3,6);
            if(y==2 || y==5 || y==8)
                return new Bounds(3,6,6,9);
        }
        if(x==2 || x==5 || x==8)
        {
            if(y==0 || y==3 || y==6)
                return new Bounds(6,9,0,3);
            if(y==1 || y==4 || y==7)
                return new Bounds(6,9,3,6);
            if(y==2 || y==5 || y==8)
                return new Bounds(6,9,6,9);
        }
        return new Bounds();
    }

    public Move calculate(int[][] moves,int x_min,int x_max,int y_min,int y_max)
    {
        Move move;
        int m1,m2,m3,m11,m22,m33,m31,m13;
        move=null;
        //parcurgere pe coloane a macroboard-ului
        for (int y = y_min; y < y_max; y++) 
        {
            m1=moves[x_min][y];//casuta sus
            m2=moves[x_min+1][y];//casuta mijloc
            m3=moves[x_max-1][y];//casuta jos
            //verific daca pot sa inchid macroboard-ul
            if(m1==1 && m2==1)
            {
                if(m3==0)
                {
                    move=new Move(x_max-1,y);
                    break;
                }
            }
            if(m1==1 && m3==1)
            {
                if(m2==0)
                {
                    move=new Move(x_min+1,y);
                    break;
                }
            }
            if (m3==1 && m2==1)
            {
                if(m1==0)
                {
                    move=new Move(x_min,y);
                    break;
                }
            }
            //daca nu am putut sa il inchid verific daca adversarul este la 
            //o mutare decisiva distanta de a inchida macroboard-ul si il blochez
            if(m1==2 && m2==2)
            {
                if(m3==0)
                {
                    move=new Move(x_max-1,y);
                    break;
                }
            }
            if(m1==2 && m3==2)
            {
                if(m2==0)
                {
                    move=new Move(x_min+1,y);
                    break;
                }
            }
            if (m3==2 && m2==2)
            {
                if(m1==0)
                {
                    move=new Move(x_min,y);
                    break;
                }
            }
        }
        //daca nu am gasit o mutare decisiva pe coloane
        if(move==null)
        {
            //parcurg pe linii macroboard-ul
            for (int x = x_min; x < x_max; x++) 
            {
                m1=moves[x][y_min]; // casuta stanga
                m2=moves[x][y_min+1]; //casuta mijloc
                m3=moves[x][y_max-1]; //casuta dreapta
                //verific daca pot sa inchid macroboard-ul
                if(m1==1 && m2==1)
                {
                    if(m3==0)
                    {
                        move=new Move(x,y_max-1);
                        break;
                    }
                }
                if(m1==1 && m3==1)
                {
                    if(m2==0)
                    {
                        move=new Move(x,y_min+1);
                        break;
                    }
                }
                if (m3==1 && m2==1)
                {
                    if(m1==0)
                    {
                        move=new Move(x,y_min);
                        break;
                    }
                }
                //daca nu am putut sa il inchid verific daca adversarul este la 
                //o mutare decisiva distanta de a inchida macroboard-ul si il blochez
                if(m1==2 && m2==2)
                {
                    if(m3==0)
                    {
                        move=new Move(x,y_max-1);
                        break;
                    }
                }
                if(m1==2 && m3==2)
                {
                    if(m2==0)
                    {
                        move=new Move(x,y_min+1);
                        break;
                    }
                }
                if (m3==2 && m2==2)
                {
                    if(m1==0)
                    {
                        move=new Move(x,y_min);
                        break;
                    }
                }
            }
        }
        // daca nici pe linii nu am gasit o mutare decisiva verific diagonalele
        if(move==null)
        {
            m11=moves[x_min][y_min];
            m22=moves[x_min+1][y_min+1];
            m33=moves[x_max-1][y_max-1];
            m13=moves[x_min][y_max-1];
            m31=moves[x_max-1][y_min];
            //verific daca pot sa inchid macroboard-ul
            if(m11==1 && m22==1)
            {
                if(m33==0)
                    return new Move(x_max-1,y_max-1);
            }
            if(m11==1 && m33==1)
            {
                if(m22==0)
                    return new Move(x_min+1,y_min+1);
            }
            if(m22==1 && m33==1)
            {
                if(m11==0)
                    return new Move(x_min,y_min);
            }
            if(m13==1 && m31==1)
            {
                if(m22==0)
                    return new Move(x_min+1,y_min+1);
            }
            if(m13==1 && m22==1)
            {
                if(m31==0)
                    return new Move(x_max-1,y_min);
            }
            if(m22==1 && m31==1)
            {
                if(m13==0)
                    return new Move(x_min,y_max-1);
            }
            //daca nu am putut sa il inchid verific daca adversarul este la 
            //o mutare decisiva distanta de a inchida macroboard-ul si il blochez
            if(m11==2 && m22==2)
            {
                if(m33==0)
                    return new Move(x_max-1,y_max-1);
            }
            if(m11==2 && m33==2)
            {
                if(m22==0)
                    return new Move(x_min+1,y_min+1);
            }
            if(m22==2 && m33==2)
            {
                if(m11==0)
                    return new Move(x_min,y_min);
            }
            if(m31==2 && m13==2)
            {
                if(m22==0)
                    return new Move(x_min+1,y_min+1);
            }
            if(m22==2 && m13==2)
            {
                if(m31==0)
                    return new Move(x_max-1,y_min);
            }
            if(m31==2 && m22==2)
            {
                if(m13==0)
                    return new Move(x_min,y_max-1);
            }
        }
        //daca nu am gasit nimic decisiv in macroboard returnez null
        return move; 
    }
	public Move makeTurn(Field field) {
                //returneaza tabela 9x9 cu mutari de la runda curenta
		int[][] moves = field.getAvailableMoves();
                Move move;
                move=null;
                int y_min=0,y_max=3,x_min=0,x_max=3;
                //verifica daca macroboard-ul activ este stanga sus
                if(field.isInActiveMicroboard(1, 1))
                {
                    System.out.println("1");
                    //stabilesc limitele macroboard-ului in tabela mare
                    x_min=0;
                    x_max=3;
                    y_min=0;
                    y_max=3;
                    //incerc sa gasesc o mutare decisiva in macroboard
                    move=this.calculate(moves,x_min,x_max,y_min,y_max);
                    if(move!=null)
                        return move;
                }
                //verifica daca macroboard-ul activ este mijloc sus
                if(field.isInActiveMicroboard(1, 4))
                {
                    System.out.println("2");
                    //stabilesc limitele macroboard-ului in tabela mare
                    x_min=0;
                    x_max=3;
                    y_min=3;
                    y_max=6;
                    //incerc sa gasesc o mutare decisiva in macroboard
                    move=this.calculate(moves,x_min,x_max,y_min,y_max);
                    if(move!=null)
                        return move;
                }
                //verifica daca macroboard-ul activ este dreapta sus
                if(field.isInActiveMicroboard(1, 7))
                {
                    System.out.println("3");
                    //stabilesc limitele macroboard-ului in tabela mare
                    x_min=0;
                    x_max=3;
                    y_min=6;
                    y_max=9;
                    //incerc sa gasesc o mutare decisiva in macroboard
                    move=this.calculate(moves,x_min,x_max,y_min,y_max);
                    if(move!=null)
                        return move;
                }
                //verifica daca macroboard-ul activ este mijloc stanga
                if(field.isInActiveMicroboard(4, 1))
                {
                    System.out.println("4");
                    //stabilesc limitele macroboard-ului in tabela mare
                    x_min=3;
                    x_max=6;
                    y_min=0;
                    y_max=3;
                    //incerc sa gasesc o mutare decisiva in macroboard
                    move=this.calculate(moves,x_min,x_max,y_min,y_max);
                    if(move!=null)
                        return move;
                }
                //verifica daca macroboard-ul activ este mijloc mijloc
                if(field.isInActiveMicroboard(4, 4))
                {
                    System.out.println("5");
                    //stabilesc limitele macroboard-ului in tabela mare
                    x_min=3;
                    x_max=6;
                    y_min=3;
                    y_max=6;
                    //incerc sa gasesc o mutare decisiva in macroboard
                    move=this.calculate(moves,x_min,x_max,y_min,y_max);
                    if(move!=null)
                        return move;
                }
                //verifica daca macroboard-ul activ este mijloc dreapta
                if(field.isInActiveMicroboard(4, 7))
                {
                    System.out.println("6");
                    //stabilesc limitele macroboard-ului in tabela mare
                    x_min=3;
                    x_max=6;
                    y_min=6;
                    y_max=9;
                    //incerc sa gasesc o mutare decisiva in macroboard
                    move=this.calculate(moves,x_min,x_max,y_min,y_max);
                    if(move!=null)
                        return move;
                }
                //verifica daca macroboard-ul activ este stanga jos
                if(field.isInActiveMicroboard(7, 1))
                {
                    System.out.println("7");
                    //stabilesc limitele macroboard-ului in tabela mare
                    x_min=6;
                    x_max=9;
                    y_min=0;
                    y_max=3;
                    //incerc sa gasesc o mutare decisiva in macroboard
                    move=this.calculate(moves,x_min,x_max,y_min,y_max);
                    if(move!=null)
                        return move;
                }
                //verifica daca macroboard-ul activ este mijloc jos
                if(field.isInActiveMicroboard(7, 4))
                {
                    System.out.println("8");
                    //stabilesc limitele macroboard-ului in tabela mare
                    x_min=6;
                    x_max=9;
                    y_min=3;
                    y_max=6;
                    //incerc sa gasesc o mutare decisiva in macroboard
                    move=this.calculate(moves,x_min,x_max,y_min,y_max);
                    if(move!=null)
                        return move;
                }
                //verifica daca macroboard-ul activ este drepta jos
                if(field.isInActiveMicroboard(7, 7))
                {
                    System.out.println("9");
                    //stabilesc limitele macroboard-ului in tabela mare
                    x_min=6;
                    x_max=9;
                    y_min=6;
                    y_max=9;
                    //incerc sa gasesc o mutare decisiva in macroboard
                    move=this.calculate(moves,x_min,x_max,y_min,y_max);
                    if(move!=null)
                        return move;
                }
                //daca nu am gasit nicio mutare decisiva pun prima mutare 
                //valabila din ultimul macroboard activ gasit
                for (int x = x_min; x < x_max; x++)
                    for (int y = y_min; y < y_max; y++)
                        if(moves[x][y]==0)
                            return new Move(x,y);
                return move;
	}


	public static void main(String[] args) {
		BotParser parser = new BotParser(new BotStarter());
		parser.run();
	}
}

