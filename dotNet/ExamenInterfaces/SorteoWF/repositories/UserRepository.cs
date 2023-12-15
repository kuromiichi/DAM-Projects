using SorteoWF.models;

namespace SorteoWF.repositories;

public class UserRepository
{
    private readonly List<User> _users = new();

    public void Add(User user)
    {
        _users.Add(user);
    }
    
    public List<User> GetAll()
    {
        return _users.ToList();
    }

    public void Clear()
    {
        _users.Clear();
    }
}
