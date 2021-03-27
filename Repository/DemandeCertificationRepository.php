<?php

namespace App\Repository;

use App\Entity\DemandeCertification;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method DemandeCertification|null find($id, $lockMode = null, $lockVersion = null)
 * @method DemandeCertification|null findOneBy(array $criteria, array $orderBy = null)
 * @method DemandeCertification[]    findAll()
 * @method DemandeCertification[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class DemandeCertificationRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, DemandeCertification::class);
    }

    // /**
    //  * @return DemandeCertification[] Returns an array of DemandeCertification objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('d')
            ->andWhere('d.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('d.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?DemandeCertification
    {
        return $this->createQueryBuilder('d')
            ->andWhere('d.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
