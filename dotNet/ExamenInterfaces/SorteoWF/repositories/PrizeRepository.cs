using SorteoWF.models;

namespace SorteoWF.repositories;
public class PrizeRepository
{
    private readonly List<Prize> _prizes = new();

    public void Add(Prize prize)
    {
        _prizes.Add(prize);
    }

    public List<Prize> GetAll()
    {
        return _prizes.ToList();
    }

    public void Clear()
    {
        _prizes.Clear();
    }
}
