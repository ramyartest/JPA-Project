package RR;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class MobileApplicationDAO 
{

    private final SessionFactory sessionFactory;

    public MobileApplicationDAO(SessionFactory sessionFactory) 
    {
        this.sessionFactory = sessionFactory;
    }

    public void saveOrUpdateMobileApplication(MobileApplication mobileApp) 
    {
        try (Session session = sessionFactory.openSession()) 
        {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(mobileApp);
            transaction.commit();
        }
    }

    public MobileApplication getMobileApplicationById(Long id) 
    {
        try (Session session = sessionFactory.openSession()) 
        {
            return session.get(MobileApplication.class, id);
        }
    }

    public void deleteMobileApplication(Long id) 
    {
        try (Session session = sessionFactory.openSession()) 
        {
            Transaction transaction = session.beginTransaction();
            MobileApplication mobileApp = session.get(MobileApplication.class, id);
            if (mobileApp != null) 
            {
                session.delete(mobileApp);
            }
            transaction.commit();
        }
    }

    public List<MobileApplication> getAllMobileApplications() 
    {
        try (Session session = sessionFactory.openSession()) 
        {
            return session.createQuery("FROM MobileApplication", MobileApplication.class).list();
        }
    }

    public List<MobileApplication> getApplicationsByDeveloper(String developer) 
    {
        try (Session session = sessionFactory.openSession()) 
        {
            return session.createQuery("FROM MobileApplication WHERE developer = :developer", MobileApplication.class)
                    .setParameter("developer", developer)
                    .list();
        }
    }

    public void updateApplicationVersion(Long id, String newVersion) 
    {
        try (Session session = sessionFactory.openSession()) 
        {
            Transaction transaction = session.beginTransaction();
            MobileApplication mobileApp = session.get(MobileApplication.class, id);
            if (mobileApp != null) 
            {
                mobileApp.setVersion(newVersion);
                session.saveOrUpdate(mobileApp);
            }
            transaction.commit();
        }
    }
}
