<?php

namespace App\Repository;

use App\Entity\Certification;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;



use App\Repository\CertificationRepository;
use App\Form\CertificationType;

/**
 * @method Certification|null find($id, $lockMode = null, $lockVersion = null)
 * @method Certification|null findOneBy(array $criteria, array $orderBy = null)
 * @method Certification[]    findAll()
 * @method Certification[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class CertificationRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Certification::class);
    }

    // /**
    //  * @return Certification[] Returns an array of Certification objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('c.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Certification
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */

    public function findCertifByNom($Nom){
        return $this->createQueryBuilder('Certification')
            ->where('Certification.Nom LIKE :nom')
            ->setParameter('nom', '%'.$Nom.'%')
            ->getQuery()
            ->getResult();
    }


    public function findByDate()
    {
        return $this->createQueryBuilder('s')
            ->orderBy('s.DatePassage', 'Asc')
            ->getQuery()

            ->getResult()
            ;
    }
    public function findByInf()
    {
        return $this->createQueryBuilder('s')
        ->where('s.Type LIKE :nom')
        ->setParameter('nom', 'Informatique')
            ->getQuery()
            ->getResult()
            ;
    }

    public function findByMec()
    {
        return $this->createQueryBuilder('s')
        ->where('s.Type LIKE :nom')
        ->setParameter('nom', 'Mecanique')
            ->getQuery()
            ->getResult()
            ;
    }

    public function findByElec()
    {
        return $this->createQueryBuilder('s')
        ->where('s.Type LIKE :nom')
        ->setParameter('nom', 'Electronique')
            ->getQuery()
            ->getResult()
            ;
    }

    public function findByJavanbr()
    {
        return $this->createQueryBuilder('s')
        ->where('s.Nom LIKE :nom')
        ->setParameter('nom', 'JAVA')
        ->select('COUNT(s.Nom)')

          ->getQuery()
          
          ->getResult()
            ;
    }


}