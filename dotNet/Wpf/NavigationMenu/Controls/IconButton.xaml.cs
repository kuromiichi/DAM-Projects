using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace NavigationMenu;

public partial class IconButton : RadioButton
{
    public static readonly DependencyProperty IconProperty =
        DependencyProperty.Register("Icon",
                                    typeof(string), 
                                    typeof(IconButton));

    public static readonly DependencyProperty TextProperty =
        DependencyProperty.Register("Text",
                                    typeof(string),
                                    typeof(IconButton));

    public string Icon
    {
        get => (string)GetValue(IconProperty);
        set => SetValue(IconProperty, value);
    }

    public string Text
    {
        get => (string)GetValue(TextProperty);
        set => SetValue(TextProperty, value);
    }

    public IconButton() => InitializeComponent();
}
