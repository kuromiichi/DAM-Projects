namespace SorteoWF.models;

public class Prize
{
    public string Name { get; }

    public Prize(string name)
    {
        Name = name;
    }

    public override string ToString()
    {
        return Name;
    }
}
