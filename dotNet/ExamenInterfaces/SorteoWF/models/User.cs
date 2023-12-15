namespace SorteoWF.models;

public class User
{
    public string Name { get; }

    public User(string name)
    {
        Name = name;
    }

    public override string ToString()
    {
        return Name;
    }
}
