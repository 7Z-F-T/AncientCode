using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace MidAutumn
{
    public partial class Form1 : Form
    {
        static int state ;
        static int player ; //1 for Houjie, 2 for Dulijing
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            state = -2;
            button1.Text = "继续";
            button2.Visible = false;
            button3.Visible = false;
            textBox1.Text = "啦啦啦，各位看官大家好！\r\n今天是农历八月十五，中秋佳节！\r\n我们敬爱的侯大侠和pp的杜小猪正坨在一起欢度中秋！";
        }

        private void button1_Click(object sender, EventArgs e)
        {   
            switch(state)
            {
                case -2:
                    textBox1.Text = "正当他们俩甜蜜地享受节日的美好时，好折腾的他们却为了争论谁更聪明而展开了激烈的战斗！\r\n";
                    state = -1;
                    break;
                case -1:
                    textBox1.Text = "到底谁能更胜一筹呢？亲爱的玩家朋友，请做出你们的选择吧！\r\n你们将带领侯大侠或是杜小猪来证明自己的实力！";
                    state = 0;
                    break;
                case 0:
                    button1.Visible = false;
                    button2.Visible = true;
                    button3.Visible = true;
                    button2.Text = "侯大侠";
                    button3.Text = "杜小猪";
                    textBox1.Text = "你选择扮演侯大侠还是杜小猪？";
                    break;
                case 10:
                    textBox1.Text = "啊，杜小猪她来啦。\r\n\r\n杜小猪：“我给你出个谜语吧，你肯定答不上来！哼，不信？来吧听着：\r\n大猩猩最怕什么？”";
                    state = 11;
                    break;
                case 11:
                    textBox1.Text = "哼，这道题早就跟我说过千八百遍了，还在这哄我。我觉得杜猪这人啊，就是喜欢成心跟我逗闷子。好多笑话说了好多遍还跟我讲，当我没听过啊，我每次都忍了罢了，现在竟然还来糊弄我。\r\n";
                    button1.Visible = false;
                    button2.Visible = true;
                    button3.Visible = true;
                    button2.Text = "k她两句";
                    button3.Text = "先忍了吧";
                    break;
        
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            switch(state)
            {
                case 0:
                    textBox1.Text = "明智的选择，现在你就是侯大侠，努力去打败杜小猪吧";
                    state = 10;
                    button2.Visible = false;
                    button3.Visible = false;
                    button1.Visible = true;
                    break;
                case 11:
                    textBox1.Text = "不能忍了，我的教训她两句！\r\n 侯大侠：“答案不就是平行线吗？你个猪连这个都不懂”\r\n杜小猪：“啊，这都知道啊...” \r\n侯大侠：“废话，你都跟我说过好多遍了，你好烦那，能不能说点新的？”";
                    state = 12;
                    button2.Visible = false;
                    button3.Visible = false;
                    button1.Visible = true;
                    break;
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            switch (state)
            {
                case 0:
                    textBox1.Text = "明智的选择，现在你就是杜小猪，用力去干掉侯大侠吧";
                    state = 20;
                    button2.Visible = false;
                    button3.Visible = false;
                    button1.Visible = true;
                    break;
                case 11:
                    textBox1.Text = "算了还是不跟她争了！\r\n 侯大侠：“我不知道答案哦，告诉我吧好么？”\r\n杜小猪：“呵呵好啊，答案是平行线，因为平行线没有香蕉，哈哈哈哈...”";
                    state = 13;
                    button2.Visible = false;
                    button3.Visible = false;
                    button1.Visible = true;
                    break;
            }
        }
    }
}
