/*
 *Name: David Audu
 *ASSIGNMENT 1
 * Project Name: Movie Database
 -------------------------------------------------------------------------
 You can add a movie to the database by filling out the form to the left
 of the interface and clicking on the ADD button. Input marked as "*" are
 required. 

 The Movie Select COMBOBOX in the middle of the interface is used to select
 and view movie records from the database. Records selected are displayed(READ
 ONLY) on the form on the left side of the interface. To edit CLICK the EDIT 
 button to change values associated with a selected movie record and then 
 CLICK the UPDATE button for records to be updated to the database. 

 To the right of the interface is a GRID VIEWER and search functionality
 underneath to suport SEARCH based on movie name and/or genre. Search
 results are display in the DATA GRID VIEW.
 
 
 
 */


using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace DavidAudu_A1
{
    public partial class Form1 : Form
    {
        SqlConnection scon;
        SqlCommand cmd;
        public Form1()
        {
            InitializeComponent();
        }
        public void create_connection()//Used to create and open connection to database
        {
            scon = new SqlConnection("Data Source=DESKTOP-48TUBPV\\SQLEXPRESS;Initial Catalog=Movie;Integrated Security=True");
            cmd = scon.CreateCommand();
            scon.Open();
        }
        private void Label4_Click(object sender, EventArgs e)
        {

        }

        private void ComboBox2_SelectedIndexChanged(object sender, EventArgs e)
        {
           

        }

        private void Button1_Click(object sender, EventArgs e)//Used to add movies to the database based on SQL INSERT query
        {
            try
            {
             
                    String insert = "";
                    int return_rows = -1;
                    create_connection();

                    //both isbn and rating not given
                    if (isbn.Text == "" && comboBox2.Text == "")
                    {
                        insert = "insert into Movies(MovieName, Date, Location, Genre, Duration, Price, MovieID) " +
                            "values ('" + movieName.Text + "','" + dateTimePicker1.Value + "', '" + location.Text + "', '" + comboBox1.Text + "', '" + duration.Text + "', '" + Double.Parse(price.Text) + "', '" + int.Parse(movieId.Text) + "');";
                        cmd.CommandText = insert;
                        return_rows = cmd.ExecuteNonQuery();
                        if (return_rows > 0)
                        {
                            MessageBox.Show("Movie added to database");
                            movieList.Items.Clear();
                            updateList();
                            scon.Close();
                        }

                    }

                    //isbn is not given
                   if (isbn.Text == "" && comboBox2.Text != "")
                   {
                    insert = "insert into Movies(MovieName, Date, Location, Genre, Rating, Duration, Price, MovieID) " +
                        "values ('" + movieName.Text + "','" + dateTimePicker1.Value + "', '" + location.Text + "', '" + comboBox1.Text + "',  '" + comboBox2.Text + "','" + duration.Text + "', '" + Double.Parse(price.Text) + "', '" + int.Parse(movieId.Text) + "');";
                    cmd.CommandText = insert;
                    return_rows = cmd.ExecuteNonQuery();
                    if (return_rows > 0)
                    {
                        MessageBox.Show("Movie added to database");
                        movieList.Items.Clear();
                        updateList();
                        scon.Close();
                    }

                   }

                   //rating is not given --FIX
                   if (isbn.Text != "" && comboBox2.Text == "")
                   {
                    insert = "insert into Movies(MovieName, ISBN Date, Location, Genre, Duration, Price, MovieID) " +
                        "values ('" + movieName.Text + "',  '" + isbn.Text + "','" + dateTimePicker1.Value + "', '" + location.Text + "', '" + comboBox1.Text + "', '" + duration.Text + "', '" + Double.Parse(price.Text) + "', '" + int.Parse(movieId.Text) + "');";
                    cmd.CommandText = insert;
                    return_rows = cmd.ExecuteNonQuery();
                    if (return_rows > 0)
                    {
                        MessageBox.Show("Movie added to database");
                        movieList.Items.Clear();
                        updateList();
                        scon.Close();
                    }

                  }
                    //any mandatory field not given
                   if (movieName.Text == "" || location.Text == "" || comboBox1.Text == "" || duration.Text == "" || price.Text == "" || movieId.Text == "" || (int.Parse(duration.Text)<0) || (Double.Parse(price.Text)<0))
                   {
                        MessageBox.Show("Fields marked with * cannot be empty");
                   }
                 
                 //Invalid movie name
                 if (stringIsAllNumbers(movieName.Text))
                 {
                    MessageBox.Show("Movie name can be alphanumric but not only digits");
                 }

                // all fields are given
                else if((movieName.Text != "") && (isbn.Text != "") && (dateTimePicker1.Text!="") && (location.Text != "") && (comboBox1.Text!="") && (comboBox2.Text != "") && (duration.Text != "") && (price.Text != "") && (movieId.Text != ""))
                {
                    insert = "insert into Movies(MovieName, ISBN, Date, Location, Genre, Rating, Duration, Price, MovieID) values ('" + movieName.Text + "','"
                        + int.Parse(isbn.Text) + "',  '" + dateTimePicker1.Value + "', '" + location.Text + "',       " +
                        " '" + comboBox1.Text + "', '" + int.Parse(comboBox2.Text) + "', '" + int.Parse(duration.Text) + "', '" + Double.Parse(price.Text) + "',  '" + int.Parse(movieId.Text) + "' );";
                    cmd.CommandText = insert;
                    return_rows = cmd.ExecuteNonQuery();
                    if (return_rows > 0)
                    {
                        MessageBox.Show("Movie added to database");
                        movieList.Items.Clear();
                        updateList();
                        scon.Close();
                    }
                }

                
            }

            catch (SqlException err)
            {
                MessageBox.Show("Movie ID is already used. Please use ID other than: " + getAllMovieIDs());
            }
        }

        public String getAllMovieIDs() //Helper method used to retrieve all movie id's to support and assist other methods
        {
            String result = "";
            create_connection();
            String selectAllID = "select MovieID from Movies";
            cmd.CommandText = selectAllID;
            SqlDataReader rd = cmd.ExecuteReader();
            while (rd.Read())
            {
                result = result + "  "+ rd[0].ToString() + " ";

            }
            scon.Close();

            return result;
        }

        private void DateTimePicker1_ValueChanged(object sender, EventArgs e)
        {

        }

        private void TextBox4_TextChanged(object sender, EventArgs e)
        {

        }

        private void Form1_Load(object sender, EventArgs e)
        {
            //movie select combobox is updated with movie names from database
            updateList();

            //both update and edit buttons are disabled 
            update.Enabled = false;
            modify.Enabled = false;
        }

        private void Label10_Click(object sender, EventArgs e)
        {

        }


        private void MovieList_SelectedIndexChanged(object sender, EventArgs e) //Used to select movie and display record in the form
        {
            //movieList.Items.Clear();
            
            try
            {   
                //EDIT button is enabled
                modify.Enabled = true;

                String selectAllonSelected = "select MovieName, ISBN, Date,Location, Genre, Rating, Duration, Price, MovieID from Movies where MovieName='" + movieList.SelectedItem.ToString() + "'";
                create_connection();
                cmd.CommandText = selectAllonSelected;
                SqlDataReader rd = cmd.ExecuteReader();


                while (rd.Read())
                {

                    movieName.Text = rd[0].ToString();
                    isbn.Text = rd[1].ToString();
                    dateTimePicker1.Text = rd[2].ToString();
                    location.Text = rd[3].ToString();
                    comboBox1.Text = rd[4].ToString();
                    comboBox2.Text = rd[5].ToString();
                    duration.Text = rd[6].ToString();
                    price.Text = rd[7].ToString(); 
                    movieId.Text = rd[8].ToString();
                }

                //form input componets set to READ ONLY STATUS
                movieName.ReadOnly = true;
                comboBox1.Enabled = false;
                comboBox2.Enabled = false;
                isbn.ReadOnly = true;
                location.Enabled = false;
                duration.ReadOnly = true;
                price.ReadOnly = true;
                movieId.ReadOnly = true;
                dateTimePicker1.Enabled = false;

                scon.Close();
            }
            catch (Exception err)
            {
                MessageBox.Show(err.ToString());
            }
        }

        private void updateList() //Helper method used to update MOVIE SELECT COMBOBOX IN REAL TIME
        {
            String selectAllMovieNames = "select MovieName from Movies";
            create_connection();
            cmd.CommandText = selectAllMovieNames;
            SqlDataReader rd = cmd.ExecuteReader();
            while (rd.Read())
            {
                movieList.Items.Add(rd[0].ToString());

            }
            scon.Close();
        }

        private void Button3_Click(object sender, EventArgs e)
        {
 

        }

        private void Delete_Click(object sender, EventArgs e) //Used to delet movie records based on SQL DELET query
        {
            try
            {
                DialogResult result = MessageBox.Show("Are You Sure You Want to DELETE?", "Delete", MessageBoxButtons.OKCancel, MessageBoxIcon.Information);
                if (result.Equals(DialogResult.OK))
                {
                    create_connection();

                    String deleteMovie = "delete from Movies where MovieID='" + int.Parse(movieId.Text) + "'";

                    cmd.CommandText = deleteMovie;
                    int return_rows = cmd.ExecuteNonQuery();

                    if (return_rows > 0)
                    {
                        MessageBox.Show("Movie deleted from database");
                        movieList.Items.Clear();
                        updateList();
                    }


                    scon.Close();
                }
                else
                {
                }
                
            }
            catch (Exception err)
            {
                MessageBox.Show(err.ToString());
            }
        }

        private void Search_Click(object sender, EventArgs e) //Used to search for movies in the database based on Movie Name and/or Genre
        {
            try
            {
                if (search1.Text == "" && (comboBoxSearch.SelectedIndex > -1))
                {
                    create_connection();
                    String search = "select * from Movies where Genre='" + comboBoxSearch.SelectedItem.ToString() + "'  ";
                    cmd.CommandText = search;
                    SqlDataReader rd = cmd.ExecuteReader();
                    DataTable dt = new DataTable();
                    dt.Load(rd);
                    dataGridView1.DataSource = dt;
                }
                if (search1.Text != "" && (comboBoxSearch.SelectedIndex < 0))
                {
                    create_connection();
                    String search = "select * from Movies where MovieName='" + search1.Text + "'  ";
                    cmd.CommandText = search;
                    SqlDataReader rd = cmd.ExecuteReader();
                    DataTable dt = new DataTable();
                    dt.Load(rd);
                    dataGridView1.DataSource = dt;
                }
                if (search1.Text != "" && (comboBoxSearch.SelectedIndex > -1))
                {
                    create_connection();
                    String search = "select * from Movies where MovieName='" + search1.Text + "' AND Genre='" + comboBoxSearch.SelectedItem.ToString() + "'  ";
                    cmd.CommandText = search;
                    SqlDataReader rd = cmd.ExecuteReader();
                    DataTable dt = new DataTable();
                    dt.Load(rd);
                    dataGridView1.DataSource = dt;
                }
                if (search1.Text == "" && (comboBoxSearch.SelectedIndex < 0))
                {
                    MessageBox.Show("Please enter movie name and/or genre ");
                }
                

            }
            catch (Exception err)
            {
                MessageBox.Show(err.ToString());
            }

        }

        private void ComboBoxSearch_SelectedIndexChanged(object sender, EventArgs e)
        {
            
        }

        private void Button4_Click(object sender, EventArgs e) //Used to edit movie record. It depends on the update button
        {
            //the update button is enabled
            update.Enabled = true;

            movieName.ReadOnly = false;
            comboBox1.Enabled = true;
            comboBox2.Enabled = true;
            isbn.ReadOnly = false;
            location.Enabled = true;
            duration.ReadOnly = false;
            price.ReadOnly = false;
           
            dateTimePicker1.Enabled = true;

            //add and delet buttons are disabled
            add.Enabled = false;
            delete.Enabled = false;
        }

        private void ComboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

            
 
        }

        private void Button1_Click_1(object sender, EventArgs e)
        {

        }

        private void Update_Click(object sender, EventArgs e) //Used to update movie records based on SQL UPDATE query
        {
            try
            {

                String update = "";
                int return_rows = -1;
                create_connection();

                if (isbn.Text == "" && comboBox2.Text == "")
                {
                    update = "update Movies set MovieName='"+ movieName.Text + "', Date='"+ dateTimePicker1.Value + "', Location='"+ location.Text + "', Genre='"+ comboBox1.Text + "', Duration='"+ duration.Text + "', Price='"+ Double.Parse(price.Text) + "' where MovieID= '"+movieId.Text+"';";
                    cmd.CommandText = update;
                    return_rows = cmd.ExecuteNonQuery();
                    if (return_rows > 0)
                    {
                        MessageBox.Show("Movie record updated");
                        movieList.Items.Clear();
                        updateList();
                        scon.Close();
                    }

                }
                if (isbn.Text != "" && comboBox2.Text == "")
                {
                    update = "update Movies set MovieName='" + movieName.Text + "', ISBN='"+isbn.Text+"', Date='" + dateTimePicker1.Value + "', Location='" + location.Text + "', Genre='" + comboBox1.Text + "', Duration='" + duration.Text + "', Price='" + Double.Parse(price.Text) + "' where MovieID= '" + movieId.Text + "';";
                    cmd.CommandText = update;
                    return_rows = cmd.ExecuteNonQuery();
                    if (return_rows > 0)
                    {
                        MessageBox.Show("Movie record updated");
                        movieList.Items.Clear();
                        updateList();
                        scon.Close();
                    }

                }
                if (isbn.Text == "" && comboBox2.Text != "")
                {
                    update = "update Movies set MovieName='" + movieName.Text + "', Date='" + dateTimePicker1.Value + "', Location='" + location.Text + "', Genre='" + comboBox1.Text + "', Rating='"+comboBox2.Text+"', Duration='" + duration.Text + "', Price='" + Double.Parse(price.Text) + "' where MovieID= '" + movieId.Text + "';";
                    cmd.CommandText = update;
                    return_rows = cmd.ExecuteNonQuery();
                    if (return_rows > 0)
                    {
                        MessageBox.Show("Movie record updated");
                        movieList.Items.Clear();
                        updateList();
                        scon.Close();
                    }

                }

                else
                {
                    update = "update Movies set MovieName='" + movieName.Text + "', ISBN='" + isbn.Text + "', Date='" + dateTimePicker1.Value + "', Location='" + location.Text + "', Genre='" + comboBox1.Text + "', Rating='"+comboBox2.Text+"', Duration='" + duration.Text + "', Price='" + Double.Parse(price.Text) + "' where MovieID= '" + movieId.Text + "';";
                    cmd.CommandText = update;
                    return_rows = cmd.ExecuteNonQuery();
                    if (return_rows > 0)
                    {
                        MessageBox.Show("Movie record updated");
                        movieList.Items.Clear();
                        updateList();
                        scon.Close();
                    }

                }


            }

            catch (SqlException err)
            {
                MessageBox.Show("Movie ID is already used. Please use ID other than: " + getAllMovieIDs());
            }
        }

        private void MovieId_TextChanged(object sender, EventArgs e)
        {

        }

        private void Location_TextChanged(object sender, EventArgs e)
        {

        }

        private void Location_SelectedIndexChanged(object sender, EventArgs e)
        {

        }
        private Boolean stringIsAllNumbers(String s)
        {
            int check = 0;
            for(int i=0; i<s.Length; i++)
            {
                if (Char.IsDigit(s[i])) {check++;}
            }

            if (check == s.Length) { return true; }
            else { return false; }

        }

        private void RadioButton1_CheckedChanged(object sender, EventArgs e)
        {
            this.BackColor = Color.FromArgb(0, 0, 255);
        }

        private void RadioButton2_CheckedChanged(object sender, EventArgs e)
        {
            this.BackColor = Color.FromArgb(0, 255, 0);
        }

        private void RadioButton3_CheckedChanged(object sender, EventArgs e)
        {
            this.BackColor = Color.FromArgb(255, 0, 0);
        }

        private void RadioButton4_CheckedChanged(object sender, EventArgs e)
        {
            this.BackColor = Color.FromArgb(240, 240, 240);
        }

        private void DataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }
    }
}
