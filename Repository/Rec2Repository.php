<?php

namespace App\Repository;

use App\Entity\Rec2;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Rec2|null find($id, $lockMode = null, $lockVersion = null)
 * @method Rec2|null findOneBy(array $criteria, array $orderBy = null)
 * @method Rec2[]    findAll()
 * @method Rec2[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class Rec2Repository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Rec2::class);
    }

    // /**
    //  * @return Rec2[] Returns an array of Rec2 objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('r.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Rec2
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
