using System.Threading;

namespace C__Programming
{
    public partial class Form1 : Form
    {
        private Mutex mutex = new Mutex();
        private Thread[] threadHandlers = new Thread[2];
        private CancellationTokenSource[] tokenSource = new CancellationTokenSource[2];
        public Form1()
        {
            InitializeComponent();
            Control.CheckForIllegalCrossThreadCalls = false;

            tokenSource[0] = new CancellationTokenSource();
            tokenSource[1] = new CancellationTokenSource();
        }

        private void threadFunc1(string c, CancellationToken token)
        {
            while (true) {
                if(token.IsCancellationRequested) { break; }
                mutex.WaitOne();
                textBox1.Text += c;
                mutex.ReleaseMutex();
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            threadHandlers[0] = new Thread(new ThreadStart(() => { threadFunc1("a", tokenSource[0].Token); }));
            threadHandlers[1] = new Thread(new ThreadStart(() => { threadFunc1("AA", tokenSource[1].Token); }));
            
            threadHandlers[0].Start();
            threadHandlers[1].Start();

            button1.Enabled = false;
            button2.Enabled = true;
        }

        private void button2_Click(object sender, EventArgs e)
        {
            tokenSource[0].Cancel();
            tokenSource[1].Cancel();

            threadHandlers[0].Join();
            threadHandlers[1].Join();

            button2.Enabled = false;
            button1.Enabled = true;
        }
    }
}
