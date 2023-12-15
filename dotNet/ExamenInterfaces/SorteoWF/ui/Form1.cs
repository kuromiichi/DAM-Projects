using SorteoWF.repositories;
using SorteoWF.models;
using SorteoWF.ui;
using System.Diagnostics;

namespace SorteoWF
{
    public partial class Form1 : Form
    {
        private UserRepository _userRepo = new();
        private PrizeRepository _prizeRepo = new();
        private Random random = new();

        public Form1()
        {
            InitializeComponent();
        }

        private void btnAddName_Click(object sender, EventArgs e)
        {
            List<User> users = _userRepo.GetAll();
            CreateUser createUser = new();
            if (createUser.ShowDialog() == DialogResult.OK)
            {
                if (users.Exists(u => u.Name == createUser.name))
                {
                    MessageBox.Show(
                        "Ya existe un usuario con ese nombre",
                        "Error",
                        MessageBoxButtons.OK,
                        MessageBoxIcon.Error
                    );
                }
                else
                {
                    _userRepo.Add(new(createUser.name));
                    listBox1.Items.Clear();
                    listBox1.Items.AddRange(_userRepo.GetAll().ToArray());
                }
            }
        }

        private void btnAddPrize_Click(object sender, EventArgs e)
        {
            List<Prize> prizes = _prizeRepo.GetAll();
            CreatePrize createPrize = new();
            if (createPrize.ShowDialog() == DialogResult.OK)
            {
                if (prizes.Exists(p => p.Name == createPrize.name))
                {
                    MessageBox.Show(
                        "Ya existe un premio con ese nombre",
                        "Error",
                        MessageBoxButtons.OK,
                        MessageBoxIcon.Error
                    );
                }
                else
                {
                    _prizeRepo.Add(new(createPrize.name));
                    listBox2.Items.Clear();
                    listBox2.Items.AddRange(_prizeRepo.GetAll().ToArray());
                }
            }
        }

        private void btnClear_Click(object sender, EventArgs e)
        {
            listBox1.Items.Clear();
            listBox2.Items.Clear();

            _userRepo.Clear();
            
            _prizeRepo.Clear();
        }

        private void btnStart_Click(object sender, EventArgs e)
        {
            List<User> users = _userRepo.GetAll();
            List<Prize> prizes = _prizeRepo.GetAll();

            if (prizes.Count > 0 && users.Count >= prizes.Count)
            {
                List<User> winners = new();
                for (int i = 0; i < prizes.Count; i++)
                {
                    User winner = users[random.Next(users.Count)];
                    winners.Add(winner);
                    users.Remove(winner);
                }

                string message = "Ganadores:\n\n";
                for (int i = 0; i < winners.Count; i++)
                {
                    message += prizes[i].Name + " -> " + winners[i].Name;
                }

                MessageBox.Show(
                    message,
                    "Sorteo realizado",
                    MessageBoxButtons.OK
                );

                listBox1.Items.Clear();
                listBox1.Items.AddRange(_userRepo.GetAll().ToArray());
            }
            else
            {
                MessageBox.Show(
                    "No se puede hacer el sorteo con estos participantes y premios",
                    "Error",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Error
                );
            }
        }
    }
}
